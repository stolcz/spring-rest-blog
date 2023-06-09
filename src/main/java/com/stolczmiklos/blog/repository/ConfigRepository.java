package com.stolczmiklos.blog.repository;

import com.stolczmiklos.blog.domain.ConfigEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigRepository extends JpaRepository<ConfigEntity, Long> {

    ConfigEntity getById(Long configID);

}
