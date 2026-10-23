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
        Assertions.assertThat(alunoResponse.getId()).isEqualTo(1L);
        Assertions.assertThat(alunoResponse.getId()).isGreaterThan(0);

    }

    



}
