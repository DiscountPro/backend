package com.discount_pro.web_service.profiles.application.internal.outboundservices.acl;

import com.discount_pro.web_service.iam.interfaces.acl.IamContextFacade;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExternalIamService {
    private final IamContextFacade iamContextFacade;

    public ExternalIamService(IamContextFacade iamContextFacade) {
        this.iamContextFacade = iamContextFacade;
    }

    // retornar el id del usuario creado
    public Long createUser(String username, String password, String role) {
        // el role es de tipo list y se le pasa un solo valor
        //convertir el role a una lista
        return iamContextFacade.createUser(username, password, List.of(role));
    }
}
