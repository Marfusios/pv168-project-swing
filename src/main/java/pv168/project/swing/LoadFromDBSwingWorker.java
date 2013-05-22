package pv168.project.swing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pv168.project.Book;
import pv168.project.Disk;
import pv168.project.Entity;

import javax.swing.*;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created with IntelliJ IDEA.
 * User: m4r10
 * Date: 5/21/13
 * Time: 6:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class LoadFromDBSwingWorker extends SwingWorker<List<Entity>,Void> {

    final static Logger log = LoggerFactory.getLogger(LoadFromDBSwingWorker.class);

    @Override
    protected List<Entity> doInBackground() throws Exception {
            return MainWindow.thisWindow.getManagerEntities().getEntitiesList();
    }

    @Override
    protected void done() {
       try {
            BooksTableModel modelBooks = MainWindow.thisWindow.getModelBooks();
            DisksTableModel modelDisks = MainWindow.thisWindow.getModelDisks();

            modelBooks.removeAll();
            modelDisks.removeAll();

            for(Entity entity : get())
            {
                if(entity instanceof Book)
                {
                    modelBooks.addBook((Book)entity);
                }
                else if(entity instanceof Disk)
                {
                   modelDisks.addDisk((Disk)entity);
                }
            }

           log.info("Data were loaded from DB and added to models.");

           MainWindow.thisWindow.setSaveAble(true);

        } catch (ExecutionException ex) {
            //TODO log it
        } catch (InterruptedException ex) {
            // K tomuto by v tomto případě nemělo nikdy dojít (viz níže)
            throw new RuntimeException("Operation interrupted (this should never happen)",ex);
        }
    }
}
