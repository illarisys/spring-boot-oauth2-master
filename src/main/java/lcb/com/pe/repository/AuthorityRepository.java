package lcb.com.pe.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import lcb.com.pe.domain.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, String> {
}
