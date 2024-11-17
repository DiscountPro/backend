package com.discount_pro.web_service.iam.domain.services;

import com.discount_pro.web_service.iam.domain.model.entities.Role;
import com.discount_pro.web_service.iam.domain.model.queries.GetAllRolesQuery;
import com.discount_pro.web_service.iam.domain.model.queries.GetRoleByNameQuery;

import java.util.List;
import java.util.Optional;

public interface RoleQueryService {
  List<Role> handle(GetAllRolesQuery query);
  Optional<Role> handle(GetRoleByNameQuery query);
}
