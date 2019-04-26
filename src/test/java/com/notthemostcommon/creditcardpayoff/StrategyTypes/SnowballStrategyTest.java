package com.notthemostcommon.creditcardpayoff.StrategyTypes;

import com.notthemostcommon.creditcardpayoff.Debts.Debt;
import com.notthemostcommon.creditcardpayoff.Debts.DebtRepository;
import com.notthemostcommon.creditcardpayoff.Debts.TestDebtBuilder;
import com.notthemostcommon.creditcardpayoff.User.AppUser;
import com.notthemostcommon.creditcardpayoff.User.UserRepository;
import com.notthemostcommon.creditcardpayoff.WithMockCustomUser;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class SnowballStrategyTest {

    @Spy
    @InjectMocks
    private SnowballStrategy snowballStrategy;

    @Mock
    private UserRepository mockUserRepository;

    @Mock
    private DebtRepository mockDebtRepository;

    @Mock
    private TestDebtBuilder mockDebtBuilder;

    @Mock
    Principal mockPrincipal;

    @Mock
    private AppUser mockUserStub;

    @Mock
    private Debt mockDebt;


    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Should Return Balance of 3 items")
    public void testTotalBalanceOfMultiItems() throws Exception {
//        mockUserStub.setUsername(mockPrincipal.getName());
        List<Debt> mockDebtList = new ArrayList<>();

        Debt mock1 = new TestDebtBuilder()
                .withUser(mockUserStub)
                .build();
        mockDebtList.add(mock1);
        Debt mock2 = new TestDebtBuilder()
                .withUser(mockUserStub)
                .build();
        mock2.setBalance(2f);
        mock2.setId(2L);
        mockDebtList.add(mock2);
        Debt mock3 = new TestDebtBuilder()
                .withUser(mockUserStub)
                .build();
        mock3.setBalance(500f);
        mock3.setId(3L);
        mockDebtList.add(mock3);


//        when(mockUserRepository.findByUsername(mockUserStub.getUsername())).thenReturn(mockUserStub);
//        when(mockDebtRepository.findByAppUserId(mockUserStub.getId())).thenReturn(mockDebtList);
        List<Debt> actual = snowballStrategy.arrangedList(mockDebtList);

        /* TODO write better tests */
        assertEquals(3, mockDebtList.size());
        assertEquals(mockDebtList, actual);



    }

    @Test
    @DisplayName("Returns payoff for a single debt")
    public void returnsPayoffOneDebt(){
        List<Debt> mockDebtList = new ArrayList<>();
        when(mockPrincipal.getName()).thenReturn("test@test.com");
        when(mockUserRepository.findByUsername("test@test.com")).thenReturn(mockUserStub);
        when(mockUserStub.getId()).thenReturn(1234L);
        when(mockDebtRepository.findByAppUserId(1234L)).thenReturn(mockDebtList);

        mockUserStub.setUsername(mockPrincipal.getName());

        Debt mock1 = new TestDebtBuilder()
                .withUser(mockUserStub)
                .build();
        mockDebtList.add(mock1);

        List<Object> snowballResult = snowballStrategy.payoffSchedule(mockPrincipal);
//        when(mockUserStub.getUsername()).thenReturn("user");
//
//
        Object results = snowballResult.get(0);


        System.out.println(snowballResult.toArray());

//        assertEquals(3, months );

    }

}