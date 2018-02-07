package service.serviceInteface;

import entity.Work;

import java.util.List;


/**
 * Interface for the ability to implement a standard set commands to work with the database.
 * Here you can write your lacking methods for later implementation on {@link service.serviceImpl.WorkServiceImpl}.
 */
public interface WorkService {

    void save(Work work);

    List<Work> getAll();

    Work findById(Long id);

    void update(Work work);

    void delete(Work work);
}
