package com.discount_pro.web_service.iam.interfaces.rest.transform;

import com.discount_pro.web_service.iam.domain.model.aggregates.User;
import com.discount_pro.web_service.iam.domain.model.entities.Role;
import com.discount_pro.web_service.iam.interfaces.rest.resources.UserResource;

public class UserResourceFromEntityAssembler {

  public static UserResource toResourceFromEntity(User user) {
    var roles = user.getRoles().stream()
        .map(Role::getStringName)
        .toList();
    return new UserResource(user.getId(), user.getUsername(), roles);
  }
}
