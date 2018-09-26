/**
 * 
 */
package com.ab.wallet.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ab.wallet.entities.PlayerAccount;

/**
 * @author Bovas
 *
 */
@Repository
public interface PlayerAccountRepository extends CrudRepository<PlayerAccount, Long>{

}
