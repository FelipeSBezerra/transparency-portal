package com.devfelipe.transparencyportal.common.resource.hateoas.response.field;

public record UriList(
        String findAllUri,
        String createUri,
        String putUri,
        String deleteUri
) {
}
