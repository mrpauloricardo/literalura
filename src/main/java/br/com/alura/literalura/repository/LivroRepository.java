package br.com.alura.literalura.repository;

import br.com.alura.literalura.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {

    @Query("SELECT l FROM Livro l")
    List<Livro> findAllWithAutores();

    List<Livro> findByIdioma(String idiomaLivro);

    @Query("SELECT DISTINCT l.idioma FROM Livro l")
    List<String> findDistinctIdiomas();

    List<Livro> findTop10ByOrderByQuantidadeDownloadsDesc();
}
