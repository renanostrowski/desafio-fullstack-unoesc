package br.edu.unoesc.desafiofullstackunoesc.reposository;

import br.edu.unoesc.desafiofullstackunoesc.model.dto.MunicipioDTO;
import br.edu.unoesc.desafiofullstackunoesc.model.entity.Municipio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface iMunicipioRepository extends JpaRepository<Municipio, Long> {
    @Query("select m from Municipio m where m.codigoIBGE = :codigo")
    Municipio findByCodigoIBGE(long codigo);

    @Query("select m from Municipio m where m.nomeIBGE = :nome")
    Municipio findByNome(String nome);
}
