package com.ws.web;

import com.ws.entidades.PermisosRoles;
import com.ws.servicios.impl.PermisosRolesServiceImpl;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/permisos")
@CrossOrigin("*")
public class ControllerPermisosRoles {

    @Autowired
    private PermisosRolesServiceImpl permisosRolesService;


    @GetMapping("/buscarPorRol/{idRol}")
    public Maybe<PermisosRoles> buscar(@PathVariable("idRol") long idRol){
        return permisosRolesService.buscarPorRol(idRol);
    }

    @PostMapping("/guardar")
    public Single<PermisosRoles> guardar(@RequestBody PermisosRoles permisosRoles){
        return permisosRolesService.guardar(permisosRoles);
    }

    @PostMapping("/actualizar/{idRol}")
    public Single<PermisosRoles> guardar(@RequestBody PermisosRoles permisosRoles, @PathVariable("idRol") long idRol){
        permisosRoles.setIdRol(idRol);
        return permisosRolesService.actualizar(permisosRoles);
    }



}
