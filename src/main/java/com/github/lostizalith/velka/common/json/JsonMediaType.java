package com.github.lostizalith.velka.common.json;

public class JsonMediaType {

    private JsonMediaType() {
        // Utility class should not have constructor
    }

    // rfc6902
    public static final String JSON_PATCH_UTF8 = "application/json-patch+json;charset=UTF-8";
    // rfc7396
    public static final String MERGE_PATCH_UTF8 = "application/merge-patch+json;charset=UTF-8";
}
