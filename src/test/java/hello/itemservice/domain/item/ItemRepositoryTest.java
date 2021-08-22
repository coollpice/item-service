package hello.itemservice.domain.item;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class ItemRepositoryTest {

    private ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach() {
        itemRepository.clearStore();
    }

    @Test
    void saveTest() {
        //given
        Item item = new Item("JPABOOK" , 2000 , 200);
        //when
        Item saveItem = itemRepository.save(item);
        //then
        Item findItem = itemRepository.findById(item.getId());
        assertThat(saveItem).isEqualTo(findItem);
    }

    @Test
    void findAll() {
        //given
        Item item = new Item("JPABOOK" , 2000 , 200);
        Item item2 = new Item("JAVABOOK", 1000, 100);
        //when
        itemRepository.save(item);
        itemRepository.save(item2);

        List<Item> list = itemRepository.findAll();

        //then
        assertThat(list.size()).isEqualTo(2);
        assertThat(list).contains(item, item2);

    }

    @Test
    void updateItem() {
        //given
        Item item = new Item("JPABOOK" , 2000 , 200);
        Item saveItem = itemRepository.save(item);
        Long itemId = saveItem.getId();

        //when
        Item updateItem = new Item("JPABOOK2", 4000, 400);
        itemRepository.update(itemId , updateItem);
        //then
        Item findItem = itemRepository.findById(itemId);
        assertThat(findItem.getItemName()).isEqualTo(updateItem.getItemName());
        assertThat(findItem.getPrice()).isEqualTo(updateItem.getPrice());
        assertThat(findItem.getQuantity()).isEqualTo(updateItem.getQuantity());
    }

}