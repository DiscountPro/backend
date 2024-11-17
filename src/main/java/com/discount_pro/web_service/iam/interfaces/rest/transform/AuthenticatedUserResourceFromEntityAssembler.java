package com.discount_pro.web_service.iam.interfaces.rest.transform;

import com.discount_pro.web_service.iam.domain.model.aggregates.User;
import com.discount_pro.web_service.iam.interfaces.rest.resources.AuthenticatedUserResource;

public class AuthenticatedUserResourceFromEntityAssembler {

  public static AuthenticatedUserResource toResourceFromEntity(User user, String token) {
    return new AuthenticatedUserResource(user.getId(), user.getUsername(), token);
  }
}
