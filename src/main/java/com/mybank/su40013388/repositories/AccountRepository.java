package com.mybank.su40013388.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mybank.su40013388.entities.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("Update Account a set a.accBal=a.accBal + ?1 where a.accNo=?2")
	void addAmount(Long amount, Long fromAcc);

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("Update Account b set b.accBal=b.accBal - ?1 where b.accNo=?2")
	void deductAmount(Long amount, Long toAcc);
}
