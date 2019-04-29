package com.notthemostcommon.creditcardpayoff.User.Payment;

import com.notthemostcommon.creditcardpayoff.User.AppUser;
import com.notthemostcommon.creditcardpayoff.User.UserRepository;
import com.notthemostcommon.creditcardpayoff.User.dto.AdditionalPaymentUpdateDTO;
import com.notthemostcommon.creditcardpayoff.User.dto.UserQueryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    UserRepository userRepository;


    @Override
    public UserQueryDTO updatePayment(Long id, AdditionalPaymentUpdateDTO additionalPaymentUpdateDTO) {
        if (userRepository.findById(id).isPresent()) {
            AppUser existingUser = userRepository.findById(id).get();

            existingUser.setAdditionalPayment(additionalPaymentUpdateDTO.getAdditionalPayment());
            System.out.println("existing user" + existingUser.getAdditionalPayment());
            AppUser updatedUser = userRepository.save(existingUser);

            return new UserQueryDTO(updatedUser.getId(),
                    updatedUser.getFirstName(),
                    updatedUser.getLastName(),
                    updatedUser.getUsername(),
                    updatedUser.getAdditionalPayment(),
                    updatedUser.getTotalInterestAccrued());
        }else {
            return null;
        }
    }
}
