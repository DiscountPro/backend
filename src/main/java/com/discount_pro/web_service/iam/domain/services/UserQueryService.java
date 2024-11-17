package com.discount_pro.web_service.iam.domain.services;

import com.discount_pro.web_service.iam.domain.model.aggregates.User;
import com.discount_pro.web_service.iam.domain.model.queries.GetAllUsersQuery;
import com.discount_pro.web_service.iam.domain.model.queries.GetUserByIdQuery;
import com.discount_pro.web_service.iam.domain.model.queries.GetUserByUsernameQuery;

import java.util.List;
import java.util.Optional;

public interface UserQueryService {
  List<User> handle(GetAllUsersQuery query);
  Optional<User> handle(GetUserByIdQuery query);
  Optional<User> handle(GetUserByUsernameQuery query);
}
