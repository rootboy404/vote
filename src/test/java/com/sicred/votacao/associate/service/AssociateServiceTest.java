package com.sicred.votacao.associate.service;

import com.sicred.votacao.domain.associate.exception.CpfNotFoundException;
import com.sicred.votacao.domain.associate.model.Associate;
import com.sicred.votacao.domain.associate.repository.AssociateRepository;
import com.sicred.votacao.domain.associate.service.AssociateService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.BDDMockito.given;
import static org.mockito.MockitoAnnotations.openMocks;

public class AssociateServiceTest {

    private AssociateService associateService;

    @Mock
    private AssociateRepository associateRepository;

    @BeforeEach
    public void setup(){
        openMocks(this);
        associateService = new AssociateService(associateRepository);
    }

    @Nested
    @DisplayName("Find associate")
    class FindAssociateByCpf{

        @Test
        @DisplayName("should return a associate when search by cpf")
        void shouldFindAssociateByCpfAndReturnAssociate(){
            Associate associateReturned = createAssociate();
            given(associateRepository.findByCpf("34998909832")).willReturn(Optional.of(associateReturned));
            Associate associate = associateService.findUserByCpf("34998909832");
            assertThat(associate).isEqualTo(associateReturned);
        }

        @Test
        @DisplayName("should return a associate when search by cpf not Exist")
        void shouldFindAssociateByCpfAndReturnException(){
            given(associateRepository.findByCpf("34998909832")).willReturn(Optional.empty());
            assertThatThrownBy(() -> {
                associateService.findUserByCpf("34998909832");
            }).isInstanceOf(CpfNotFoundException.class);

            CpfNotFoundException exception = assertThrows(CpfNotFoundException.class, () -> {
                associateService.findUserByCpf("34998909832");
                fail("should not pass here!");
            });
        }

    }

    private Associate createAssociate() {
        return new Associate(1L,"34998909832","João Pessoa");
    }

}
