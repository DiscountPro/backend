package com.discount_pro.web_service.iam.domain.services;

import com.discount_pro.web_service.iam.domain.model.commands.SeedRolesCommand;

public interface RoleCommandService {
  void handle(SeedRolesCommand command);
}
