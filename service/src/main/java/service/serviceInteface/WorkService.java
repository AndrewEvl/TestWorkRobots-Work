package service.serviceInteface;

import entity.Work;

import java.util.List;

public interface WorkService {

    void save (Work work);

    List<Work> getAll ();

    Work findById (Long id);

    void update (Work work);

    void delete (Work work);

    Work minId ();
}
