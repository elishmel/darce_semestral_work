package cz.cvut.fit.nebesluk.semestral.nebesluk_darce.api;

import cz.cvut.fit.nebesluk.semestral.nebesluk_darce.api.dto.client.ClientDto;
import cz.cvut.fit.nebesluk.semestral.nebesluk_darce.api.dto.client.ClientMapper;
import cz.cvut.fit.nebesluk.semestral.nebesluk_darce.api.dto.client.ClientSmallDto;
import cz.cvut.fit.nebesluk.semestral.nebesluk_darce.api.dto.client.NewClientDto;
import cz.cvut.fit.nebesluk.semestral.nebesluk_darce.domain.Client;
import cz.cvut.fit.nebesluk.semestral.nebesluk_darce.exceptions.EntityAlreadyExistsException;
import cz.cvut.fit.nebesluk.semestral.nebesluk_darce.exceptions.EntityNotExistsException;
import cz.cvut.fit.nebesluk.semestral.nebesluk_darce.service.ClientService;
import cz.cvut.fit.nebesluk.semestral.nebesluk_darce.service.ImageService;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Collection;

@RestController
@RequestMapping("/api/client")
public class ClientController {

    @Autowired
    AuthenticationManager authenticationManager;

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

    @GetMapping("/username/{username}")
    public ClientDto ReadByUsername(@PathVariable String username){
        try{
            return clientService.ReadByUsername(username).map(mapper::toDto).orElseThrow(EntityNotExistsException::new);
        } catch (EntityNotExistsException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ClientDto ReadById(@PathVariable Long id){
        try{
            return clientService.ReadById(id).map(mapper::toDto).orElseThrow(EntityNotExistsException::new);
        } catch(EntityNotExistsException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/auth")
    public void Register(@RequestBody NewClientDto dto){
        try{
            clientService.Register(mapper.toEntity(dto),dto.getPassword());
        } catch (EntityAlreadyExistsException e){
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/auth")
    public boolean Login(@RequestHeader("Authorization") String baseAuthor){
        var decoded = new String(Base64.decodeBase64(baseAuthor));
        var split = decoded.split(":");
        var username = split[0];
        var password = split[1];
        Authentication authentication;
        try{
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username,password)
            );
        } catch (BadCredentialsException e){
            return false;
        }

        return true;
    }

    @PutMapping("/{id}")
    public ClientDto Update(@PathVariable Long id,@RequestBody NewClientDto dto){
        try{
            var oldUser = clientService.ReadById(id);
            Client c = mapper.toEntity(dto);
            c.setClient_id(id);
            c.setDateCreated(oldUser.get().getDateCreated());
            c.setDateLastLogon(LocalDateTime.now());

            clientService.UpdateCredentials(oldUser.get().getUsername(),dto.getUsername(), dto.getPassword());

            return mapper.toDto(clientService.Update(c));
        } catch (EntityNotExistsException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } catch (NullPointerException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public void Delete(@PathVariable Long id){
        try{
            clientService.DeleteCredentials(id);
            clientService.DeleteById(id);
        }catch (EntityNotExistsException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

}
