package cz.cvut.fit.nebesluk.semestral.nebesluk_darce.api;

import cz.cvut.fit.nebesluk.semestral.nebesluk_darce.api.dto.client.ClientDto;
import cz.cvut.fit.nebesluk.semestral.nebesluk_darce.api.dto.client.ClientMapper;
import cz.cvut.fit.nebesluk.semestral.nebesluk_darce.api.dto.client.ClientSmallDto;
import cz.cvut.fit.nebesluk.semestral.nebesluk_darce.api.dto.client.NewClientDto;
import cz.cvut.fit.nebesluk.semestral.nebesluk_darce.api.dto.item.ItemMapper;
import cz.cvut.fit.nebesluk.semestral.nebesluk_darce.api.dto.item.NewItemDto;
import cz.cvut.fit.nebesluk.semestral.nebesluk_darce.domain.Client;
import cz.cvut.fit.nebesluk.semestral.nebesluk_darce.exceptions.EntityAlreadyExistsException;
import cz.cvut.fit.nebesluk.semestral.nebesluk_darce.exceptions.EntityNotExistsException;
import cz.cvut.fit.nebesluk.semestral.nebesluk_darce.service.ClientService;
import cz.cvut.fit.nebesluk.semestral.nebesluk_darce.service.ImageService;
import cz.cvut.fit.nebesluk.semestral.nebesluk_darce.service.ItemService;
import cz.cvut.fit.nebesluk.semestral.nebesluk_darce.service.TagService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;

@RestController
@RequestMapping("/client")
public class ClientController {

    private ClientService clientService;
    private ClientMapper mapper;

    public ClientController(ClientService clientService_,ImageService imageService_){
        clientService = clientService_;
        mapper = new ClientMapper(imageService_);
    }

    @GetMapping()
    public Collection<ClientSmallDto> ReadAll(){
        return clientService.ReadAll().stream().map(mapper::toSmallDto).toList();
    }

    @GetMapping("/{id}")
    public ClientDto ReadById(@PathVariable Long id){
        try{
            return clientService.ReadById(id).map(mapper::toDto).orElseThrow(EntityNotExistsException::new);
        } catch(EntityNotExistsException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/register")
    public void Register(@RequestBody NewClientDto dto){
        try{
            clientService.Register(mapper.toEntity(dto),dto.getPassword());
        } catch (EntityAlreadyExistsException e){
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
    }



}
