package com.agendadakota.agendadakota.controller;

import com.agendadakota.agendadakota.dto.ServicioDTO;
import com.agendadakota.agendadakota.dto.TurnoDTO;
import com.agendadakota.agendadakota.dto.UsuarioDTO;
import com.agendadakota.agendadakota.entities.Rol;
import com.agendadakota.agendadakota.repository.ServicioRepository;
import com.agendadakota.agendadakota.repository.TurnoRepository;
import com.agendadakota.agendadakota.repository.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final TurnoRepository turnoRepository;
    private final UsuarioRepository usuarioRepository;
    private final ServicioRepository servicioRepository;
    private final ModelMapper mapper;

    public AdminController(TurnoRepository turnoRepository, UsuarioRepository usuarioRepository, ServicioRepository servicioRepository, ModelMapper mapper) {
        this.turnoRepository = turnoRepository;
        this.usuarioRepository = usuarioRepository;
        this.servicioRepository = servicioRepository;
        this.mapper = mapper;
    }


    @GetMapping
    public String admin(Authentication auth) {
        return "Hola " + auth.getName();
    }

    @GetMapping("/dashboard")
    public Map<String, Object> dashboard (Authentication auth) {

        Map<String, Object> data = new HashMap<>();

        data.put("email", auth.getName());
        data.put("totalTurnos", turnoRepository.count());
        data.put("totalProfesionales",
                usuarioRepository.countByRol(Rol.PROFESIONAL));
        data.put("totalServicios", servicioRepository.count());

        return data;
    }

    @GetMapping("/profesionales")
    public List<UsuarioDTO> profesionales() {
        return usuarioRepository.findByRol(Rol.PROFESIONAL)
                .stream()
                .map(u -> mapper.map(u, UsuarioDTO.class))
                .toList();
    }

    @GetMapping("/servicios")
    public  List<ServicioDTO> servicios() {
        return servicioRepository.findAll()
                .stream()
                .map(s -> mapper.map(s, ServicioDTO.class))
                .toList();
    }

    @GetMapping("/turnos")
    public List<TurnoDTO> turnos() {
        return turnoRepository.findAll().stream().map(t -> {
            TurnoDTO dto = new TurnoDTO();
            dto.setId(t.getId());

            if (t.getPaciente() != null) {
                dto.setPacienteId(t.getPaciente().getId());
                dto.setPacienteNombre(
                        t.getPaciente().getNombre() + " " + t.getPaciente().getApellido()
                );
            }

            if (t.getProfesional() != null) {
                dto.setProfesionalId(t.getProfesional().getId());
                dto.setProfesionalNombre(
                        t.getProfesional().getNombre() + " " + t.getProfesional().getApellido()
                );
            }

            if (t.getServicio() != null) {
                dto.setServicioId(t.getServicio().getId());
                dto.setServicioNombre(t.getServicio().getNombre());
                dto.setDuracionMinutos(t.getServicio().getDuracionMinutos());
            }

            dto.setFechaHoraInicio(t.getFechaHoraInicio());
            dto.setFechaHoraFin(t.getFechaHoraFin());
            dto.setEstado(t.getEstado().name());

            return dto;

        }).toList();
    }

}

