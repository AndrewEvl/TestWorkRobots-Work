package service.serviceInteface;

import entity.Work;

import java.util.List;


/**
 * Interface for ability to implement of standard set of commands to work with database.
 * Here you can write your absent methods for later implementation on {@link service.serviceImpl.WorkServiceImpl}.
 */
public interface WorkService {

    void save(Work work);

    List<Work> getAll();

    Work findById(Long id);

    void update(Work work);

    void delete(Work work);
}
