package com.delegrego.api_alunos_testes.service;

import com.delegrego.api_alunos_testes.dto.AlunoRequest;
import com.delegrego.api_alunos_testes.dto.AlunoResponse;
import com.delegrego.api_alunos_testes.model.Aluno;
import com.delegrego.api_alunos_testes.repository.AlunoRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;


@ExtendWith(MockitoExtension.class)
public class AlunoServiceTeste {

    @InjectMocks
    AlunoService service;

    @Mock
    AlunoRepository repository;

    @Test
    void deveSalvarERetornarAluno(){

        //Arrange
        AlunoRequest alunoRequest = new AlunoRequest("Lucas", "lucas.polastri@gmail.com","123", LocalDate.of(2003, 05, 13), 8);

        //Stub -> parte
        Mockito.when(repository.existsByEmailIgnoreCase(alunoRequest.getEmail())).thenReturn(false);

        Aluno aluno = new Aluno(1l, alunoRequest.getNome(), alunoRequest.getEmail(), alunoRequest.getSenha(), alunoRequest.getDataNascimento(), alunoRequest.getMedia());

        Mockito.when(repository.save(Mockito.any(Aluno.class))).thenReturn(aluno);

        // Act
        AlunoResponse alunoResponse = service.cadastrarAluno(alunoRequest);



        //Assert
        Assertions.assertThat(alunoResponse).isNotNull();
        Assertions.assertThat(alunoResponse.getId()).isGreaterThan(0);
        Assertions.assertThat(alunoResponse.getId()).isEqualTo(1L);
    }
    @Test
    void deveRetornarListaAlunos(){

        //Arrange
        Aluno aluno1 = new Aluno(1L,"Lucas", "lucas.polastri@gmail.com","123", LocalDate.of(2003, 05, 13), 8);
        Aluno aluno2 = new Aluno(2L,"Roberto", "roberto.betinho@gmail.com","321", LocalDate.of(1999, 07, 18), 7);


        //Stub -> do findAll
        Mockito.when(repository.findAll()).thenReturn(List.of(aluno1, aluno2));


        //Act
        List<AlunoResponse> alunos = service.listarAlunos();


        //Assert
        Assertions.assertThat(alunos).isNotNull();
        Assertions.assertThat(alunos).hasSize(2);

    }
    // Tarefa de casa fazer os outros testes o Delete e Update
    // Fazer formulario sobre conhecimentos de front-end




}
