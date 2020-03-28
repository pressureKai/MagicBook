package com.key.magicbook.index

import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.key.keylibrary.base.BaseFragment
import com.key.keylibrary.base.ConstantValues
import com.key.keylibrary.utils.FileUtils
import com.key.magicbook.R
import com.key.magicbook.bean.BookList
import com.key.magicbook.bookpage.Config
import com.key.magicbook.bookpage.PageFactory
import com.key.magicbook.bookpage.PageWidget
import com.yanzhenjie.permission.AndPermission
import com.yanzhenjie.permission.runtime.Permission
import kotlinx.android.synthetic.main.fragment_index_mine_book.*


/**
 * created by key  on 2020/3/2
 */
class BookRackFragment :BaseFragment(){
    override fun setLayoutId(): Int {
        return  R.layout.fragment_index_mine_book
    }

    override fun initView() {
        setTitle(refresh)
        refresh.setEnableRefresh(false)
        refresh.setEnableLoadMore(false)


        val layoutManager = GridLayoutManager(activity, 3)
        layoutManager.orientation = GridLayoutManager.VERTICAL
        list.layoutManager = layoutManager


        val mineBookAdapter = MineBookAdapter(R.layout.item_book_mine)
        list.adapter = mineBookAdapter

        val arrayList = ArrayList<String>()
        arrayList.add("圣墟")



        mineBookAdapter.setNewData(arrayList)



        mineBookAdapter.setOnItemClickListener { _,
                                                 _,
                                                 _ ->

            AndPermission.with(activity)
                .runtime()
                .permission(Permission.WRITE_EXTERNAL_STORAGE)
                .onGranted {
                    saveString()
                    bookpage.visibility = View.VISIBLE
                    refresh.visibility = View.GONE
                    val config = Config.createConfig(activity)

                    val pageFactory = PageFactory.createPageFactory(activity)
                    bookpage.setPageMode(config.pageMode)
                    bookpage.setTouchListener(object :PageWidget.TouchListener{
                        override fun prePage(): Boolean {
                            pageFactory.prePage()
                            if (pageFactory.isFirstPage) {
                                return false
                            }
                            return true
                        }

                        override fun center() {

                        }

                        override fun cancel() {

                        }

                        override fun nextPage(): Boolean {
                            pageFactory.nextPage()
                            if (pageFactory.isLastPage) {
                                return false
                            }
                            return true
                        }

                    })
                    pageFactory.setPageWidget(bookpage)
                    val bookList = BookList()
                    bookList.bookname = "圣墟"
                    bookList.begin = 0
                    bookList.charset = ""
                    bookList.bookpath = ConstantValues.FILE_BOOK+"/圣墟.txt"
                    pageFactory.openBook(bookList)
                }
                .onDenied {

                }.start()

        }




    }


    inner class MineBookAdapter(res :Int) :BaseQuickAdapter<String,BaseViewHolder>(res){
        override fun convert(helper: BaseViewHolder, item: String) {
            helper.setText(R.id.book_name,item)
        }
    }





   private fun saveString(){
        FileUtils.saveString("“我看到了什么，那是真相吗？”\n" +
                "\n" +
                "    楚风灵魂悸动，他的身与心都在轻颤。\n" +
                "\n" +
                "    对岸，有一个生物！\n" +
                "\n" +
                "    他真切的看到了，绝非错觉！\n" +
                "\n" +
                "    可是，现在他眼睛剧痛，难以忍受那种腐蚀，无法持续再盯着看，眼球在被莫名物质撕裂。\n" +
                "\n" +
                "    “路到尽头，未见永恒，有凋零的强者！”\n" +
                "\n" +
                "    楚风像是在梦呓，努力想记住刚才看到的一切，很模糊，很朦胧的画面，但确实无比的重要。\n" +
                "\n" +
                "    他知道，这关乎着花粉路的未来，不能遗忘。\n" +
                "\n" +
                "    可是，任他拥有了双恒尊果位，他的记忆也在消散，并要炸开了，很难想象这涉及到了怎样的领域！\n" +
                "\n" +
                "    为什么？他脑中竟一片空白。\n" +
                "\n" +
                "    他很迷惘，连看一眼都会被针对，已被诅咒了吗？\n" +
                "\n" +
                "    他像是要失去自我，不光是记忆，连自身的存在都不能保证了，连他自己都要随着那段记忆消散了！\n" +
                "\n" +
                "    “不！”楚风握拳低吼！\n" +
                "\n" +
                "    怎会如此？\n" +
                "\n" +
                "    那到底是什么？\n" +
                "\n" +
                "    花粉路出了变故，问题就在尽头那里！\n" +
                "\n" +
                "    他看到了部分真相，可是他却被反蚀了，记不住那里的一切。\n" +
                "\n" +
                "    可以看到，楚风的身体都虚淡了，与他所看到的一样，很不真切，很朦胧，要在时光中散掉。\n" +
                "\n" +
                "    他肉身模糊，将不复存在，这是何其可怕的事件？！\n" +
                "\n" +
                "    就像是他从来没有出现过一般，这个世上仿佛从来都没有他这个人！\n" +
                "\n" +
                "    这一切太恐怖了，简直是无法想象！\n" +
                "\n" +
                "    便是真仙中的绝顶强者，以及走到腐烂尽头的大宇级生物来到这里，见到这一景况后也要惊悚，恐惧，转身逃离。\n" +
                "\n" +
                "    不然的话，连那种级数的生灵也难以摆脱，会归于朦胧，虚寂，分崩离析在这天地中。\n" +
                "\n" +
                "    生死之际，生存艰难的最后关头，楚风想到一个人，九道一口中的那位。\n" +
                "\n" +
                "    关于那个人，没有人提及姓名，他在所有人的记忆中都渐模糊下去了，逐年消散，像是从未出现过。\n" +
                "\n" +
                "    而眼前，路的尽头，也有一个生物，导致楚风记忆磨灭，脑中空白，连身体都模糊了，整个人都将消散。\n" +
                "\n" +
                "    这是同类生物吗？！\n" +
                "\n" +
                "    楚风见到了这种级数的生灵，更因为正在亲身面对，所以问题更严重！？\n" +
                "\n" +
                "    他要死去了！\n" +
                "\n" +
                "    这种死法很可悲，算是永寂，连存在过往的痕迹都被抹除。\n" +
                "\n" +
                "    甚至，连认识与熟悉他的人，都会将他遗忘。\n" +
                "\n" +
                "    这太可悲了，无比的凄凉！\n" +
                "\n" +
                "    他像是从来没有来到过这个世上，从所有人的记忆中消失，抹去。\n" +
                "\n" +
                "    纵死，亦无人知。\n" +
                "\n" +
                "    很难想象，他今天到底面对了怎样的一个存在。\n" +
                "\n" +
                "    于此之际，世界各地，许多人的脑海中关于楚风的身影果然在虚淡，不断消散，即将就此不见了。\n" +
                "\n" +
                "    这是一种非常瘆人的变化，关于一段记忆，关于一个人，居然要凭空消失，从此成为空白！\n" +
                "\n" +
                "    如果了解真相，跳出这个怪圈去审视，去观这种异变，谁不害怕？即便是堕落真仙也要为之毛骨悚然。\n" +
                "\n" +
                "    显然，有人感受到这种可怖的变化。\n" +
                "\n" +
                "    比如，与楚风有密切关系的人，第一时间觉察到不妥。\n" +
                "\n" +
                "    亚仙族，一头银色长发垂到腰际的映晓晓，莹白面孔上略带迷茫，喃喃着：“奇怪，我这是怎么了？心中空空落落，像是被斩掉了无比重要的东西，很难受，想抓却抓不住，我好像丢失了什么！”\n" +
                "\n" +
                "    “吼……”\n" +
                "\n" +
                "    异荒虎族的遗地，东大虎一声咆哮，捂着头，眼角都要瞪裂了，喘着粗气，嘶吼：“发生了什么？我的记忆断层了，有一段岁月，有一段非常重要的经历塌陷，竟连贯不起来！”\n" +
                "\n" +
                "    两界战场，周曦面色苍白，她预感到了什么，内心强烈的不安。\n" +
                "\n" +
                "    “楚风，是你吗，你怎么了，我感觉你要消失了，从我的记忆中淡去，为什么会这样？”\n" +
                "\n" +
                "    因为，她正在想楚风的事，不久前他刚离去，所以她还有些印象，但是，却也要被抹除了，她惶恐与害怕。\n" +
                "\n" +
                "    “你怎么了，为什么要从我的世界中消失，你发生……意外了吗？！”周曦落泪。\n" +
                "\n" +
                "    她来自阳间第六家族，所知道的远比常人多，自然听闻过那位的情况。\n" +
                "\n" +
                "    而现在，楚风居然连人都要从她的记忆中消失了，一定遭遇了难以想象的事。\n" +
                "\n" +
                "    “不！”\n" +
                "\n" +
                "    她不安，恐惧，可是刹那间，她就发现自己什么都没有印象了，连口中刚喊过的名字也是如此的陌生。\n" +
                "\n" +
                "    “我丢失了无比重要的东西，好心痛，我想不起来了！”周曦哭泣，她自责，揪心与忧虑，为之而恐惧。\n" +
                "\n" +
                "    “那是一个人，我记不得他了，你……快回来！”她哭着呼唤。\n" +
                "\n" +
                "    在场的人，有许多比她实力强大的人，也都露出惊容，因为他们亦被波及，被影响到了。\n" +
                "\n" +
                "    越是实力强大的生灵，所能坚持的时间越长一些，尽管区别不大，但现在他们还有些印象。\n" +
                "\n" +
                "    比如老古，还有他的老对头，大混元层次的名宿周博，全都心惊胆颤，他们能够清晰的感受到心中在“放空”。\n" +
                "\n" +
                "    “楚风，你怎么模糊了，要从我的脑海中消散？！”老古发毛，脸色煞白。\n" +
                "\n" +
                "    周博更是面色骤变，他不知道什么情况，自己老到糊涂了吗？有那样一个人，为何要从心中消失。\n" +
                "\n" +
                "    “楚风，从我的记忆中渐渐暗淡，从此不见……”昔日的秦珞音，今天的青音，站在一座山峰上，她很不解，也有些怅然，伸手在空中划过，一片虚无。\n" +
                "\n" +
                "    怪龙一脸迷茫，很恐惧，他觉得自己人生的一段经历被斩断了，不知道是该庆幸解脱了，还是该黯然长叹。\n" +
                "\n" +
                "    “楚风是谁？”不过片刻间，老古也迷惘了，不记得楚风有什么样的身份与来历，连这个名字都是陌生的。\n" +
                "\n" +
                "    “你是在说楚风？”周曦悲伤，她知道自己好像忘记了一个人，但是却不知道他是谁了，现在听到老古低语，她像是抓住了最后一根稻草，努力想忆起，可是，她却做不到，她的修为差的太远了。\n" +
                "\n" +
                "    “我不想放弃你，我要将你记起，出现在我的世界！”周曦抽噎，晶莹的泪珠不断从雪白的脸颊上滑落。\n" +
                "\n" +
                "    纵然是武疯子都露出异色，颇感意外，俯视某一片虚空。\n" +
                "\n" +
                "    “有意思，小阴间的那个人，一直有耳闻，现在竟模糊下去，将随风消散，他遇到了什么？难道是那位留下的经文，重器，被他触动后难以承受？自身要如传说那般，不复存在，这是怎样的一种体验？！”\n" +
                "\n" +
                "    武疯子思忖，连他的记忆都模糊了，有关那个人的讯息将从他心中溃散干净。\n" +
                "\n" +
                "    他知道这意味什么，那个人要死了！\n" +
                "\n" +
                "    而且，若无意外，那个人将永寂，所有痕迹都被抹灭干净。\n" +
                "\n" +
                "    他曾听到过这种传说，毕竟，武疯子所经历的岁月极其久远，接触到过不可言说的秘史不算少！\n" +
                "\n" +
                "    “楚风……是你吗？！”妖妖扬起头，洁白的下巴微向上，看起来有些倔强。\n" +
                "\n" +
                "    她一向很自信，早年就被誉为星空下第一，再加上姿容绝世，在世人眼中，可谓是风华绝代。\n" +
                "\n" +
                "    可是现在，她却露出忧色，不能从容自若了，她伸出白皙而纤秀的手指，触摸虚空。\n" +
                "\n" +
                "    她看到的与别人不一样，她竟能与楚风一般，见到“灵”！\n" +
                "\n" +
                "    她看到如同烛火般飘摇的发光粒子，大片的飞起，明灭不定。\n" +
                "\n" +
                "    在那些灵中，她仿佛看到了楚风的面庞，由灵粒子组成，正在远去，踏上一条不归路！\n" +
                "\n" +
                "    “三帝术归一，英灵照古今……”\n" +
                "\n" +
                "    妖妖迅速低吟，并且舒展身体，藕臂划过虚空，且双足踏出奇异的轨迹，她像是在进行一种古老的祭祀，又像是在结印，施展失传已久的秘术。\n" +
                "\n" +
                "    这很奇异，也很古怪。\n" +
                "\n" +
                "    她的言咒与祭舞合一，居然让空间剧烈震荡，令光阴碎片狂乱飞舞，时空共鸣，像是在接引什么！\n" +
                "\n" +
                "    “帝祭？！”\n" +
                "\n" +
                "    这一刻，羽皇吃惊，瞬间动容，他怀疑看错了！\n" +
                "\n" +
                "    那个女子，居然懂这种失传的祭舞？\n" +
                "\n" +
                "    她要做什么，难道还想召唤出一位真正的天帝不成？！\n" +
                "\n" +
                "    在妖妖的眼中，看到的与常人不同，模糊的景象，“灵”如发光的蒲公英在黑夜凋谢，飘零，远去，她想沟通！\n" +
                "\n" +
                "    ……\n" +
                "\n" +
                "    楚风的身体在虚淡，甚至部分瓦解，开始化光，化烛火，化为粒子，他越发的虚无缥缈。\n" +
                "\n" +
                "    整个人看起来都不真实了，只是一道朦胧的身影。\n" +
                "\n" +
                "    他的身与魂都在悸动，怎能如此？\n" +
                "\n" +
                "    “我只是看到部分景象，就要消散了？”\n" +
                "\n" +
                "    他不甘心，许多心愿未了，还有太多的人等着去重逢，去相见，要将转世的他们都找到，可是现在他自己却要先一步死去了。\n" +
                "\n" +
                "    “我究竟看到了什么？！”\n" +
                "\n" +
                "    模糊的画面浮现，花粉路的尽头那里……有一个强者，虽然很朦胧，但绝对是人形的，是那个生灵影响到了这一切。\n" +
                "\n" +
                "    楚风努力回想，他想死的明白。\n" +
                "\n" +
                "    花粉路的尽头，那个生灵似乎死去了，横在路上，倒在那里！\n" +
                "\n" +
                "    “长发遮面，满身是血……”楚风呓语，他回想到了这些画面！\n" +
                "\n" +
                "    这个生灵不是有意害他，而是太强大了，自身的存在就影响到了整条花粉进化路的持续与稳定！\n" +
                "\n" +
                "    “是他吗，九号口中的那位？！”\n" +
                "\n" +
                "    在心中没有彻底放空，还有残存旧忆时，楚风刹那想到这些，难道花粉路的源头，最强大的生灵竟与九道一所说的那位是同一个人？！\n" +
                "\n" +
                "    不然，何以有相似的本质，他稍微接近，记忆便要消散，连带肉身都如此。\n" +
                "\n" +
                "    可是，时代对不上，岁月不相符，两者不是同一纪的生灵。\n" +
                "\n" +
                "    “也许，有路可寻，有道可走，既然那位不属于一部古史，那…或许真有可能是同一人！”\n" +
                "\n" +
                "    “我在接近真相吗！？”\n" +
                "\n" +
                "    “我在消散，我要朝他而去？！”\n" +
                "\n" +
                "    楚风觉得，自己要死了，要瓦解了，肉身如烟，如雾，他在接近前方的天堑，这是不归路！\n" +
                "\n" +
                "    但是，他也有种错觉，像是一种仪式，要回归了！\n" +
                "\n" +
                "    霎时间，他听到了一些声音，那是……先民的祭祀音，是某种呼唤吗？\n" +
                "\n" +
                "    他要浑噩了，将死去了，很快要分崩离析，但是，在这刹那间，像是有刺目的灵光划过，他有些明悟。\n" +
                "\n" +
                "    死，不是最终的归宿！\n" +
                "\n" +
                "    这才是开始吗，他仿佛看到金戈铁马，听到喊杀震天，死后去征战？\n" +
                "\n" +
                "    一时间，他有些迷惘，他这是要去哪里？！","圣墟",ConstantValues.FILE_BOOK)
    }


}




