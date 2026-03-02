package application.com.utils.migrations;

import application.com.entities.Comment;
import application.com.entities.Post;
import application.com.repositories.CommentRepository;
import application.com.repositories.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostsCommentsMigrator {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    @Bean (name = "post-service-migrator")
    public CommandLineRunner migrate(){

        return args -> {
            postRepository.deleteById("test-post-id-1");
            postRepository.deleteById("test-post-id-2");
            postRepository.deleteById("test-post-id-3");
            commentRepository.deleteAllByPost_Id("test-post-id-1");
            commentRepository.deleteAllByPost_Id("test-post-id-2");
            commentRepository.deleteAllByPost_Id("test-post-id-3");

            Post firstPost = new Post(
                    "test-post-id-1",
                   "Как приучить кошку к когтеточке?",
                    "Одна из трех естественных потребностей кошки – точить когти. Это необходимая физиологическая" +
                            " манипуляция. Домашняя кошка точит когти там, где ей нравится. Не останавливается ни перед " +
                            "чем и раздирает своими когтями всё: обои, мебель, ковры, шторы, одежду и обувь. Когтеточка " +
                            "в доме является такой же постоянной необходимостью, как корм для кошек и наполнитель для" +
                            " кошачьего туалета."
            );
            postRepository.save(firstPost);

            Comment firstComment = new Comment(
                    "Зачастую кошка специально выбирает для отдыха предметы и места, где она может помешать хозяину - " +
                            "письменный стол, стопку рукописей, книжные полки, может забираться в картонные коробки и " +
                            "ящики. Привязанность кошки к бумаге объясняется просто: кроме шуршания и мягкости, она " +
                            "дает столь любимое тепло, аромат и уют дерева. Взрослой кошке или котёнку нужна хорошая " +
                            "когтеточка - лежанка - домик, чтобы можно было не только когти поточить, но и отдохнуть, " +
                            "поспать, почувствовать себя комфортно, как на природе. Как известно, дикие кошки, находясь" +
                            " в своей природной среде, точат когти о мягкие породы дерева, а в домашних условиях в " +
                            "качестве заменителя дерева идеально подходит картонная когтеточка - лежанка \"Когтедралка " +
                            "домашняя\"",
                    firstPost
            );
            commentRepository.save(firstComment);

            Comment secondComment = new Comment(
                    "Картонная когтеточка - лежанка имеет одно неоспоримое достоинство - она передвижная." +
                            " Зимой ее можно положить около самой теплой батареи в комнате, но, при желании, ее можно " +
                            "перенести на кухню или взять, например, в поездку... Это немаловажно для психологического " +
                            "состояния кошки. Животное получает на новом месте родную когтеточку - лежанку со своим " +
                            "запахом, что снижает вероятность стресса при смене места жительства. Любую другую большую " +
                            "висячую и тяжелую когтедерку надо снимать, перевозить, на новом месте привинчивать к стене," +
                            " а тут все просто!",
                    firstPost
            );
            commentRepository.save(secondComment);


            Post secondPost = new Post(
                    "test-post-id-2",
                "До сколько кг примерно будет в год",
                "Добрый день ! Подскажите пожалуйста , Йорк 2 мес , вес 1400. Это не много для 2-х месячного щенка )?"
                    );
            postRepository.save(secondPost);

            Comment thirdComment = new Comment(
                    "Нормально. Моему 3 месяца, весит 1600.",
                    secondPost
            );
            commentRepository.save(thirdComment);

            Comment fourthComment = new Comment(
                    "Скорее всего будет весить по максимальной планке. Главное не перекармливайте." +
                            " Не знаю почему тут пишут про разборчивый апппетит и т.п. - мой как пылесос.",
                    secondPost
            );
            commentRepository.save(fourthComment);

            Comment fivthComment = new Comment(
                    "Это у вас крупный стандарт ",
                    secondPost
            );
            commentRepository.save(fivthComment);


            Post thirdPost = new Post(
                    "test-post-id-3",
                    "Домашнее животное - Белка",
                    "Привез домой ее еще маленькой - мамка ее бросила почему то...\n" +
                            "Она привыкать начала...спала только в кармане халата мамы..или в руках... " +
                            "Потом родители уехали оставили ее на Брата..вот она уже 3 - 4 месяца сама по себе.." +
                            "Отпускаем по дому побегать - это весело и печально. Лезет везде... Все переворачивает.." +
                            "клевая, но!!! Стала наглая, РЫЧИТ на всех, прыгает, и сегодня меня цапнула с*чка!\n" +
                            "Мне некогда ей заниматься... Когда голодная в руки залазит...\n" +
                            "А так очень прикольная...у кого есть Белки дома???\n" +
                            "Может поменять ее на змею? удава какого нить?"
            );
            postRepository.save(thirdPost);

            Comment sixthComment = new Comment(
                    "Может меньше пить?",
                    thirdPost
            );
            commentRepository.save(sixthComment);

            Comment sevanthComment = new Comment(
                    "Как-то подарил своей тогда ещё будущей жене белку. Зверёк не хотел жить в клетке," +
                            " решил сделать гнездо в рукаве нового пальто, а для подстилки выгрыз приличный кусок" +
                            " шерсти из спины этого же пальто.",
                    thirdPost
            );
            commentRepository.save(sevanthComment);

        };
    }

}
