package com.stolczmiklos.blog.service;

import com.stolczmiklos.blog.domain.ConfigEntity;
import com.stolczmiklos.blog.dto.ConfigRequest;
import com.stolczmiklos.blog.repository.ConfigRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ConfigService {

    ConfigRepository configRepository;

    public ConfigService(ConfigRepository configRepository) {
        this.configRepository = configRepository;
    }

    public ConfigRequest getConfig() {
        ConfigEntity config = configRepository.getById(1L);

        return new ConfigRequest(config);
    }

    public ConfigRequest getDefaultConfig() {
        ConfigEntity config = configRepository.getById(2L);
        return new ConfigRequest(config);
    }

    public void save(ConfigRequest configRequest) {
        ConfigEntity config = configRepository.getById(1L);
        config.setRegistrationConfirmation(configRequest.isRegistrationConfirmation());
        configRepository.save(config);
    }
}
