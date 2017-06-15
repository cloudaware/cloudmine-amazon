package com.cloudaware.cloudmine.amazon.ses;

import com.amazonaws.services.simpleemail.model.GetSendStatisticsRequest;
import com.amazonaws.services.simpleemail.model.GetSendStatisticsResult;
import com.amazonaws.services.simpleemail.model.RawMessage;
import com.amazonaws.services.simpleemail.model.SendRawEmailRequest;
import com.amazonaws.services.simpleemail.model.SendRawEmailResult;
import com.cloudaware.cloudmine.amazon.AmazonUnparsedException;
import com.cloudaware.cloudmine.amazon.Constants;
import com.google.api.server.spi.config.AnnotationBoolean;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Named;
import com.google.common.base.Strings;
import org.apache.commons.codec.binary.Base64;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.BodyPart;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * User: urmuzov
 * Date: 03.23.17
 * Time: 16:28
 */
@Api(
        name = "ses",
        canonicalName = "Ses",
        title = "Amazon Simple Email Service (SES)",
        description = "Email Sending and Receiving",
        namespace = @ApiNamespace(
                ownerDomain = "cloudaware.com",
                ownerName = "CloudAware",
                packagePath = "cloudmine/amazon"
        ),
        version = "v1",
        scopes = {Constants.EMAIL_SCOPE},
        clientIds = {Constants.WEB_CLIENT_ID, Constants.API_EXPLORER_CLIENT_ID},
        apiKeyRequired = AnnotationBoolean.TRUE
)
public final class SesApi {

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "sendStatistics.get",
            path = "{region}/send-statistics"
    )
    public SendStatisticsResponse sendStatisticsGet(
            @Named("credentials") final String credentials,
            @Named("region") final String region
    ) throws AmazonUnparsedException {
        return SesCaller.get(GetSendStatisticsRequest.class, SendStatisticsResponse.class, credentials, region).execute((client, request, response) -> {
            final GetSendStatisticsResult result = client.getSendStatistics(request);
            response.setSendDataPoints(result.getSendDataPoints());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.POST,
            name = "emails.send",
            path = "{region}/emails"
    )
    public SendResponse emailsSend(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            final SendRequest request
    ) throws AmazonUnparsedException {
        return SesCaller.get(SendRawEmailRequest.class, SendResponse.class, credentials, region).execute((client, r, response) -> {
            final Session session = Session.getInstance(new Properties(), null);
            final MimeMessage mimeMessage = new MimeMessage(session);
            mimeMessage.setFrom(new InternetAddress(request.getSource()));
            for (final String to : request.getToAddresses()) {
                mimeMessage.addRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(to));
            }

            if (request.getCcAddresses() != null) {
                for (final String cc : request.getCcAddresses()) {
                    mimeMessage.addRecipient(MimeMessage.RecipientType.CC, new InternetAddress(cc));
                }
            }

            mimeMessage.setSubject(request.getSubject());

            final MimeMultipart multipart = new MimeMultipart();
            final BodyPart part = new MimeBodyPart();
            String bodyMimeType = request.getBodyMediaType();
            if (Strings.isNullOrEmpty(bodyMimeType)) {
                bodyMimeType = "text/plain";
            }

            part.setContent(request.getBody(), bodyMimeType);
            multipart.addBodyPart(part);

            if (request.getAttachments() != null) {
                if (!Strings.isNullOrEmpty(request.getZipFileName())) {
                    final ByteArrayOutputStream out = new ByteArrayOutputStream();
                    final ZipOutputStream zip = new ZipOutputStream(out);
                    for (final EmailAttachment attachment : request.getAttachments()) {
                        final byte[] bytes = Base64.decodeBase64(attachment.getBase64Content().getBytes());
                        final ZipEntry zipEntry = new ZipEntry(attachment.getName());
                        zip.putNextEntry(zipEntry);
                        zip.write(bytes);
                        zip.closeEntry();
                    }

                    zip.close();
                    final DataSource dataSource = new ByteArrayDataSource(out.toByteArray(), "application/zip");
                    final BodyPart attachmentPart = new MimeBodyPart();
                    attachmentPart.setDataHandler(new DataHandler(dataSource));
                    attachmentPart.setFileName(request.getZipFileName());
                    multipart.addBodyPart(attachmentPart);
                } else {
                    for (final EmailAttachment attachment : request.getAttachments()) {
                        final DataSource dataSource = new ByteArrayDataSource(Base64.decodeBase64(attachment.getBase64Content().getBytes()), attachment.getMimeType());
                        final BodyPart attachmentPart = new MimeBodyPart();
                        attachmentPart.setDataHandler(new DataHandler(dataSource));
                        attachmentPart.setFileName(attachment.getName());
                        multipart.addBodyPart(attachmentPart);
                    }
                }
            }

            mimeMessage.setContent(multipart);

            // Create Raw message
            final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            mimeMessage.writeTo(outputStream);
            final RawMessage rawMessage = new RawMessage(ByteBuffer.wrap(outputStream.toByteArray()));
            // Send Mail
            final SendRawEmailResult result = client.sendRawEmail(r.withRawMessage(rawMessage));

            response.setMessageId(result.getMessageId());
        });
    }
}
