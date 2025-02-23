package cz.zelo.byts.db.repository;

import cz.zelo.byts.db.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByIdid(String idid);

    long deleteByIdid(String idid);
}
