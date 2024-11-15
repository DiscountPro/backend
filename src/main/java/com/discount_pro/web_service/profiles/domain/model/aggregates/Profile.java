package com.discount_pro.web_service.profiles.domain.model.aggregates;
import com.discount_pro.web_service.profiles.domain.model.commands.CreateProfileCommand;
import com.discount_pro.web_service.profiles.domain.model.valueobjects.IamId;
import com.discount_pro.web_service.profiles.domain.model.valueobjects.Role;
import com.discount_pro.web_service.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Entity
@Table(name = "profiles")
public class Profile extends AuditableAbstractAggregateRoot<Profile> {

    @Getter
    @NotNull
    @NotBlank
    @Column(name="RUC", length=50, nullable=false)
    private String ruc;

    @Getter
    @NotNull
    @NotBlank
    @Column(name="razon_social", length=50, nullable=false)
    private String razonSocial;
    //role enun()
    @Getter
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name="role",nullable = false)
    private Role role;
    //IAM_id int
    /*@Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "iamId", column = @Column(name = "iam_id", nullable = false))
    })
    private IamId iamId;*/


    public Profile() { }
    /*public Profile(Long iamId){
        this();
        this.iamId=new IamId(iamId);
    }
    public Profile(IamId iamId){
        this();
        this.iamId=iamId;
    }*/

    public Profile(CreateProfileCommand command) {
        this.ruc = command.RUC();
        this.razonSocial = command.razonSocial();
        this.role = command.role();
    }

    /*public Long getIamId(){
        return iamId.iamId();
    }*/

    public Profile(String ruc, String razonSocial, Role role){
        this.ruc=ruc;
        this.razonSocial=razonSocial;
        this.role=role;
    }


    public void updateInformation(String ruc, String razonSocial, Role role) {
        this.ruc = ruc;
        this.razonSocial = razonSocial;
        this.role = role;
    }
}
