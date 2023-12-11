package br.com.cespga.repositories;

import br.com.cespga.model.Cargo;
import br.com.cespga.model.Medium;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MediumRepository  extends JpaRepository<Medium,Long> {

    List<Medium> findByCargo(Cargo cargo);

    List<Object> findByStatus(boolean b);
}
