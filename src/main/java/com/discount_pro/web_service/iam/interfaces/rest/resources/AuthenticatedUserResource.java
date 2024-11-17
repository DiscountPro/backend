package com.discount_pro.web_service.iam.interfaces.rest.resources;

public record AuthenticatedUserResource(Long id, String username, String token) {
}
