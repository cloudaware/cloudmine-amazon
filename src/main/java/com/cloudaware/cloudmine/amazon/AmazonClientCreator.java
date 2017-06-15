package com.cloudaware.cloudmine.amazon;

public interface AmazonClientCreator<T> {
    ClientWrapper<T> create();
}
