package dal;

import java.util.List;

import bo.Lieu;
import exceptions.BusinessException;

public interface LieuDAO {

	List<Lieu> selectAll() throws BusinessException;

	Lieu selectOneById(int idLieu) throws BusinessException;

	void insert(Lieu lieu) throws BusinessException;

	void update(Lieu lieu) throws BusinessException;

	void delete(int idLieu) throws BusinessException;

}
