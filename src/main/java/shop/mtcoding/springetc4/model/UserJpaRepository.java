package shop.mtcoding.springetc4.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<User, Long> { // <>안에 엔티티의 클래스타입과, 엔티티의 pk를 넣어줘야 함
    
}
