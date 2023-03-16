package shop.mtcoding.springetc4.model;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class UserRepository {
    private final EntityManager em; // datasource를 구현한 구현체인 EntityManger를 사용

    @Transactional // spring꺼임 원래는 서비스에서 하지만 지금은 서비스 없으니 여기서 처리
    public User save(User user) {
        em.persist(user); // persist : 하드디스크에 영구히 기록하다
        return user;
    }

    @Transactional
    public User update(User user) { // 컨트롤러가 민감한 정보는 dto로 만들어서 제외시키고 돌려줘야 하기 때문
        return em.merge(user); // merge : 기존에 있는것을 합친다 | id 안 넘겨주면 insert 해버리기 때문에 id 줘야함

    }

    @Transactional
    public void delete(User user) {
        em.remove(user);
    }

    @Transactional
    public User findById(Long id) {
        return em.find(User.class, id); // find : 찾는다
    }

    @Transactional
    public List<User> findAll(int page, int row) { // findAll은 제공안해줘서 만들어서 써야 됨
        return em.createQuery("select u from User u limit 0,2", User.class) // createQuery: Hibernate가 지원하는 쿼리 -> 문법이 조금 다름
                .setFirstResult(page) //
                .setMaxResults(2) // 한페이지에 나오는 row 수를 말함
                .getResultList();
    }

}
