package com.discount_pro.web_service.iam.interfaces.rest.transform;

import com.discount_pro.web_service.iam.domain.model.entities.Role;
import com.discount_pro.web_service.iam.interfaces.rest.resources.RoleResource;

public class RoleResourceFromEntityAssembler {

  public static RoleResource toResourceFromEntity(Role role) {
    return new RoleResource(role.getId(), role.getStringName());
  }
}
