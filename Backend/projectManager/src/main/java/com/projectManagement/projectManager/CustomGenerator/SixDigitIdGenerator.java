package com.projectManagement.projectManager.CustomGenerator;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.math.BigInteger;
import java.security.SecureRandom;

public class SixDigitIdGenerator implements IdentifierGenerator {
    private static final int Desired_Length=6;
    private static final SecureRandom secureRandom = new SecureRandom();

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object obj) throws HibernateException {
        BigInteger id;
        do {
            id = generateRandomId();
        } while (!isUniqueId(id, session));
        return id;
    }

    private BigInteger generateRandomId() {
        BigInteger randomValue = new BigInteger(Desired_Length * 5, secureRandom);
        return randomValue.mod(BigInteger.TEN.pow(Desired_Length));
    }

    private boolean isUniqueId(BigInteger id, SharedSessionContractImplementor session) {
        String queryString = "select count(entity) from User entity where entity.userId = :idValue";

        Long count = (Long) session.createQuery(queryString)
                .setParameter("idValue", id)
                .uniqueResult();

        return count != null && count == 0L;

    }
}
