package dal;

import java.util.List;

import bo.Inscription;
import exceptions.BusinessException;

public interface InscriptionDAO {

	List<Inscription> selectAll() throws BusinessException;

	List<Inscription> selectByIdSortie(int idSortie) throws BusinessException;

}
