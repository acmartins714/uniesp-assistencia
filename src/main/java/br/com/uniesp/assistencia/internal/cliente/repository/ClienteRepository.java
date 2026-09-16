package br.com.uniesp.assistencia.internal.cliente.repository;

import br.com.uniesp.assistencia.internal.cliente.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, Long>  {
    @Query("select c from ClienteEntity c where lower(c.nome) like lower(concat('%', :nome, '%')) order by c.nome asc")
    List<ClienteEntity> buscarPorNome(@Param("nome") String nome);

}
