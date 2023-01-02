package cz.cvut.fit.nebesluk.semestral.nebesluk_darce.api.dto.client;

import cz.cvut.fit.nebesluk.semestral.nebesluk_darce.api.dto.item.ItemDto;
import cz.cvut.fit.nebesluk.semestral.nebesluk_darce.api.dto.item.ItemSmallDto;
import cz.cvut.fit.nebesluk.semestral.nebesluk_darce.domain.Client;
import cz.cvut.fit.nebesluk.semestral.nebesluk_darce.service.ImageService;

public class ClientMapper {

    ImageService imageService;

    public ClientMapper(ImageService imageService_){
        imageService = imageService_;
    }

    public Client toEntity(ClientDto dto){
        var client = new Client();
        client.setClient_id(dto.getClient_id());
        client.setUsername(dto.getUsername());
        client.setRealName(dto.getRealName());
        client.setDateCreated(dto.getDateCreated());
        client.setDateLastLogon(dto.getDateLastLogon());
        client.setProfilePicture(dto.getProfilePicture());
        return client;
    }

    public Client toEntity(ClientSmallDto dto){
        var client = new Client();

        return client;
    }

    public Client toEntity(NewClientDto dto){
        var client = new Client();

        return client;
    }

    public ClientDto toDto(Client entity){
        var dto = new ClientDto();

        return dto;
    }

    public ClientSmallDto toSmallDto(Client entity){
        var dto = new ClientSmallDto();
        dto.setClient_id(entity.getClient_id());
        dto.setUsername(entity.getUsername());
        dto.setProfilePicture(entity.getProfilePicture() != null ? entity.getProfilePicture().getUrl() : null);
        return dto;
    }
}

