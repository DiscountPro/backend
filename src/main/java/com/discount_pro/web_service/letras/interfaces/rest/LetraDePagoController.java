package com.discount_pro.web_service.letras.interfaces.rest;

import com.discount_pro.web_service.letras.domain.model.commands.DeleteLetraDePagoCommand;
import com.discount_pro.web_service.letras.domain.model.queries.GetAllLetrasDePagoQuery;
import com.discount_pro.web_service.letras.domain.model.queries.GetLetraDePagoByIdQuery;
import com.discount_pro.web_service.letras.domain.model.queries.GetLetrasDePagoByClienteIdQuery;
import com.discount_pro.web_service.letras.domain.model.queries.GetLetrasDePagoByPropietarioIdQuery;
import com.discount_pro.web_service.letras.domain.services.LetraDePagoCommandService;
import com.discount_pro.web_service.letras.domain.services.LetraDePagoQueryService;
import com.discount_pro.web_service.letras.interfaces.rest.resources.CreateLetraDePagoResource;
import com.discount_pro.web_service.letras.interfaces.rest.resources.LetraDePagoResource;
import com.discount_pro.web_service.letras.interfaces.rest.resources.UpdateLetraDePagoResource;
import com.discount_pro.web_service.letras.interfaces.rest.transform.CreateLetraDePagoCommandFromResourceAssembler;
import com.discount_pro.web_service.letras.interfaces.rest.transform.LetraDePagoResourceFromEntityAssembler;
import com.discount_pro.web_service.letras.interfaces.rest.transform.UpdateLetraDePagoCommandFromResourceAssembler;
import com.discount_pro.web_service.profiles.domain.model.valueobjects.Role;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", methods = { RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE })
@RestController
@RequestMapping(value = "/api/v1/letras", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Letras", description = "Letra De Pago Management Endpoints")
public class LetraDePagoController {
    private final LetraDePagoCommandService commandService;
    private final LetraDePagoQueryService queryService;

    public LetraDePagoController(LetraDePagoCommandService commandService, LetraDePagoQueryService queryService) {
        this.commandService = commandService;
        this.queryService = queryService;
    }

    @PostMapping
    public ResponseEntity<LetraDePagoResource> createLetraDePago(@RequestBody CreateLetraDePagoResource resource) {
        var createLetraDePagoCommand = CreateLetraDePagoCommandFromResourceAssembler.toCommandFromResource(resource);
        var createdLetra = this.commandService.handle(createLetraDePagoCommand);
        var letraResource = LetraDePagoResourceFromEntityAssembler.toResourceFromEntity(createdLetra);
        return new ResponseEntity<>(letraResource, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<LetraDePagoResource>> getAllLetrasDePago(@RequestParam Role userRole) {
        if (userRole != Role.ENTIDAD_BANCARIA) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        var getAllLetrasDePagoQuery = new GetAllLetrasDePagoQuery();
        var letras = this.queryService.handle(getAllLetrasDePagoQuery);
        var letraResources = letras.stream()
                .map(LetraDePagoResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(letraResources);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LetraDePagoResource> getLetraDePagoById(@PathVariable int id, @RequestParam Role userRole) {
        if (userRole != Role.ENTIDAD_BANCARIA && userRole != Role.CLIENTE) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        var getLetraDePagoByIdQuery = new GetLetraDePagoByIdQuery(id);
        var optionalLetra = this.queryService.handle(getLetraDePagoByIdQuery);
        if (optionalLetra.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var letraResource = LetraDePagoResourceFromEntityAssembler.toResourceFromEntity(optionalLetra.get());
        return ResponseEntity.ok(letraResource);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LetraDePagoResource> updateLetraDePago(@PathVariable int id, @RequestBody UpdateLetraDePagoResource resource, @RequestParam Role userRole) {
        if (userRole != Role.ENTIDAD_BANCARIA) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        var updateLetraDePagoCommand = UpdateLetraDePagoCommandFromResourceAssembler.toCommandFromResource(id, resource);
        var optionalLetra = this.commandService.handle(updateLetraDePagoCommand);
        if (optionalLetra.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var letraResource = LetraDePagoResourceFromEntityAssembler.toResourceFromEntity(optionalLetra.get());
        return ResponseEntity.ok(letraResource);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLetraDePago(@PathVariable int id, @RequestParam Role userRole) {
        if (userRole != Role.ENTIDAD_BANCARIA) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        var deleteLetraDePagoCommand = new DeleteLetraDePagoCommand(id);
        this.commandService.handle(deleteLetraDePagoCommand);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/propietario/{propietarioId}")
    public ResponseEntity<List<LetraDePagoResource>> getLetrasDePagoByPropietarioId(@PathVariable int propietarioId, @RequestParam Role userRole) {
        if (userRole != Role.ENTIDAD_BANCARIA && userRole != Role.CLIENTE) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        var getLetrasDePagoByPropietarioIdQuery = new GetLetrasDePagoByPropietarioIdQuery(propietarioId);
        var letras = this.queryService.handle(getLetrasDePagoByPropietarioIdQuery);
        var letraResources = letras.stream()
                .map(LetraDePagoResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(letraResources);
    }

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<LetraDePagoResource>> getLetrasDePagoByClienteId(@PathVariable int clienteId, @RequestParam Role userRole) {
        if (userRole != Role.ENTIDAD_BANCARIA && userRole != Role.CLIENTE) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        var getLetrasDePagoByClienteIdQuery = new GetLetrasDePagoByClienteIdQuery(clienteId);
        var letras = this.queryService.handle(getLetrasDePagoByClienteIdQuery);
        var letraResources = letras.stream()
                .map(LetraDePagoResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(letraResources);
    }
}