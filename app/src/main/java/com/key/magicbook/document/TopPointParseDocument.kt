package com.key.magicbook.document

import android.text.TextUtils
import com.key.magicbook.base.ConstantValues
import com.key.magicbook.db.BookDetail
import org.jsoup.nodes.Document

/**
 * created by key  on 2020/5/11
 */
class TopPointParseDocument : ParseDocument() {
    /**
     *获取BookTypeFragment中的书籍列表
     */
    override fun parseBookDetails(document: Document): List<BookDetail> {
        val new = document!!.select("#newscontent > div.r > ul > li")
        var bookDetails :ArrayList<BookDetail> = ArrayList()
        for (value in new) {
            val bookName = value.select("span.s2 > a").text()
            val attr = value.select("span.s2 > a")
                .attr("href")
            val authorName = value.select("span.s5").text()
            val bookDetail = BookDetail()
            bookDetail.bookName = bookName
            bookDetail.bookUrl = attr
            bookDetail.bookAuthor = authorName
            bookDetail.bookType = ""
            if (!TextUtils.isEmpty(bookName)) {
                bookDetails!!.add(bookDetail)
            }

        }
        return bookDetails
    }

    override fun parseBookHeaders(document: Document?): List<BookDetail> {
        var headers: ArrayList<BookDetail> = ArrayList()
        val select = document!!.select("#hotcontent > div > div")
        for (value in select) {
            val bookDetail = BookDetail()
            val img = value.select(" div > div.image  > a> img").attr("src")
            val name = value.select(" div > dl > dt > a").text()
            val url = value.select(" div > dl > dt > a").attr("href")
            val intro = value.select("div > dl > dd").text()


            bookDetail.bookIntro = intro
            bookDetail.bookCover = img
            bookDetail.bookName = name
            bookDetail.bookUrl = url
            bookDetail.baseUrl = "https://www.dingdiann.com/"
            if (!TextUtils.isEmpty(intro)) {
                headers!!.add(bookDetail)
            }
        }
        return headers
    }

    override fun parseBookDetail(document: Document): BookDetail {
        val img = document.select("#fmimg > img")
        val name = document.select("#info > h1:nth-child(1)")
        val author = document.select("#info > p:nth-child(2)")
        val update = document.select("#info > p:nth-child(4)")
        val lastChapter = document.select("#info > p:nth-child(5) > a")
        val intro = document.select("#intro")
        val select = document.select("#list > dl > dd")
        val bookCover = img.attr("src")

        val bookDetail = BookDetail()
        bookDetail.baseUrl = "https://www.dingdiann.com/"
        if(bookCover.contains("http")){
            bookDetail.bookCover = bookCover
        }else{
            bookDetail.bookCover = bookDetail.baseUrl + bookCover
        }
        bookDetail.bookName = name.text()
        bookDetail.bookAuthor = author.text()
        bookDetail.lastUpdateTime = update.text()
        bookDetail.lastChapter = lastChapter.text()
        bookDetail.bookIntro = intro.text()
        bookDetail.chapterElements = select

        return bookDetail
    }

    override fun parseTypeFive(document: Document?): MutableList<BookDetail> {
        val select2 = document!!.select("#novelslist2 > div")
        val select1 = select2[0].select("ul > li")
        val bookDetailNovel = ArrayList<BookDetail>()
        for(value in select1) {
            val all = value.text()
            val bookName = value.select("a").text()
            val attr = value.select("a").attr("href")
            val authorName = all.replace(" ", "")
                .replace("/", "").replace(bookName, "")
            val bookDetail = BookDetail()
            bookDetail.bookName = bookName
            bookDetail.bookUrl = attr
            bookDetail.bookAuthor = authorName
            bookDetail.bookType = "历史军事"
            bookDetail.baseUrl = "https://www.dingdiann.com/"
            bookDetailNovel.add(bookDetail)
        }
        return bookDetailNovel
    }

    override fun parseTypeTwo(document: Document?): MutableList<BookDetail> {
        val select = document!!.select("#novelslist1 > div")
        val select1 = select[0].select("ul > li")
        val bookDetails = ArrayList<BookDetail>()
        for(value in select1){
            val all = value.text()
            val bookName = value.select("a").text()
            val attr = value.select("a").attr("href")
            val authorName = all.replace(" ", "")
                .replace("/", "").replace(bookName, "")
            val bookDetail = BookDetail()
            bookDetail.bookName = bookName
            bookDetail.bookUrl = attr
            bookDetail.bookAuthor = authorName
            bookDetail.bookType = "玄幻奇幻"
            bookDetail.baseUrl = "https://www.dingdiann.com/"
            bookDetails.add(bookDetail)
        }
        return bookDetails
    }

    override fun parseTypeOne(document: Document?): MutableList<BookDetail> {
        val scriptures = document!!.select("#hotcontent > div.r > ul > li")
        var scripturesDetails =ArrayList<BookDetail>()
        for(value in scriptures){

            val bookName =  value.select("span.s2 > a").text()
            val attr = value.select("span.s2 > a")
                .attr("href")
            val authorName = value.select("span.s5").text()
            val bookDetail = BookDetail()
            bookDetail.bookName = bookName
            bookDetail.bookUrl = attr
            bookDetail.bookAuthor = authorName
            bookDetail.bookType = "经典推荐"
            bookDetail.baseUrl = "https://www.dingdiann.com/"
            scripturesDetails.add(bookDetail)

        }
        return scripturesDetails
    }

    override fun parseTypeSix(document: Document?): MutableList<BookDetail> {
        val select2 = document!!.select("#novelslist2 > div")
        val select1 = select2[1].select("ul > li")
        val bookDetailNovel = ArrayList<BookDetail>()
        for(value in select1) {
            val all = value.text()
            val bookName = value.select("a").text()
            val attr = value.select("a").attr("href")
            val authorName = all.replace(" ", "")
                .replace("/", "").replace(bookName, "")
            val bookDetail = BookDetail()
            bookDetail.bookName = bookName
            bookDetail.bookUrl = attr
            bookDetail.bookAuthor = authorName
            bookDetail.bookType = "科幻灵异"
            bookDetail.baseUrl = "https://www.dingdiann.com/"
            bookDetailNovel.add(bookDetail)
        }
        return bookDetailNovel
    }

    override fun parseBookCover(document: Document): String {
        return ""
    }

    override fun parseTypeFour(document: Document?): MutableList<BookDetail> {
        val select = document!!.select("#novelslist1 > div")
        val select1 = select[2].select("ul > li")
        val bookDetails = ArrayList<BookDetail>()
        for(value in select1){
            val all = value.text()
            val bookName = value.select("a").text()
            val attr = value.select("a").attr("href")
            val authorName = all.replace(" ", "")
                .replace("/", "").replace(bookName, "")
            val bookDetail = BookDetail()
            bookDetail.bookName = bookName
            bookDetail.bookUrl = attr
            bookDetail.bookAuthor = authorName
            bookDetail.bookType = "都市言情"
            bookDetail.baseUrl = "https://www.dingdiann.com/"
            bookDetails.add(bookDetail)
        }
        return bookDetails
    }

    override fun parsePile(document: Document?): MutableList<BookDetail> {
        val books :ArrayList<BookDetail> = ArrayList()
        val select = document!!.select("#hotcontent > div.l > div")
        for (value in select) {
            val bookDetail = BookDetail()
            val img =
                value.select("div.image > a > img")
                    .attr("src")
            val name =
                value.select("dl > dt > a")
                    .text()
            val url =
                value.select("dl > dt > a")
                    .attr("href")
            val intro = value.select("dl > dd").text()
            bookDetail.bookIntro = intro
            bookDetail.bookCover = img
            bookDetail.bookName = name
            bookDetail.bookUrl = url
            bookDetail.baseUrl = "https://www.dingdiann.com/"
            if (!TextUtils.isEmpty(intro)) {
                books!!.add(bookDetail)
            }
        }
        val select1 = document!!.select("#novelslist1 > div")
        for (value in select1) {
            val bookDetail = BookDetail()

            val img = value.select(" div > div.image > img").attr("src")
            val name = value.select(" div > dl > dt > a").text()
            val url = value.select(" div > dl > dt > a").attr("href")
            val intro = value.select("div > dl > dd").text()

            bookDetail.bookIntro = intro
            bookDetail.bookCover = img
            bookDetail.bookName = name
            bookDetail.bookUrl = url
            bookDetail.baseUrl = "https://www.dingdiann.com/"
            if (!TextUtils.isEmpty(intro)) {
                books!!.add(bookDetail)
            }
        }

        val select2 = document!!.select("#novelslist2 > div")
        for (value in select2) {
            val bookDetail = BookDetail()
            val img = value.select(" div > div.image > img").attr("src")
            val name = value.select(" div > dl > dt > a").text()
            val url = value.select(" div > dl > dt > a").attr("href")
            val intro = value.select("div > dl > dd").text()

            bookDetail.bookIntro = intro
            bookDetail.bookCover = img
            bookDetail.bookName = name
            bookDetail.bookUrl = url
            bookDetail.baseUrl = "https://www.dingdiann.com/"
            if (!TextUtils.isEmpty(intro)) {
                books!!.add(bookDetail)
            }
        }
        return books
    }

    override fun parseBookContent(document: Document): String {
        return ""
    }

    override fun getTypeNames(): MutableList<String> {
        return arrayOf(
            "首页","玄幻奇幻", "武侠仙侠",
            "都市言情", "历史军事",
            "科幻灵异","网游竞技","女生频道").toMutableList()
    }

    override fun parseTypeThree(document: Document?): MutableList<BookDetail> {
        val select = document!!.select("#novelslist1 > div")
        val select1 = select[1].select("ul > li")
        val bookDetails = ArrayList<BookDetail>()
        for(value in select1){
            val all = value.text()
            val bookName = value.select("a").text()
            val attr = value.select("a").attr("href")
            val authorName = all.replace(" ", "")
                .replace("/", "").replace(bookName, "")
            val bookDetail = BookDetail()
            bookDetail.bookName = bookName
            bookDetail.bookUrl = attr
            bookDetail.bookAuthor = authorName
            bookDetail.bookType = "武侠仙侠"
            bookDetail.baseUrl = "https://www.dingdiann.com/"
            bookDetails.add(bookDetail)
        }
        return bookDetails
    }

    override fun parseTypeEight(document: Document?): MutableList<BookDetail> {
        val new = document!!.select("#newscontent > div.r > ul > li")
        val bookDetails = ArrayList<BookDetail>()
        for(value in new){
            val bookName =  value.select("span.s2 > a").text()
            val attr = value.select("span.s2 > a").attr("href")
            val authorName = value.select("span.s5").text()
            val bookDetail = BookDetail()
            bookDetail.bookName = bookName
            bookDetail.bookUrl = attr
            bookDetail.bookAuthor = authorName
            bookDetail.bookType = "新书"
            bookDetail.baseUrl = "https://www.dingdiann.com/"
            bookDetails.add(bookDetail)
        }
        return bookDetails
    }

    override fun getTypeUrls(): MutableList<String> {
      return  arrayOf(
            "首页","https://www.dingdiann.com/ddk_1/",
            "https://www.dingdiann.com/ddk_2/",
            "https://www.dingdiann.com/ddk_3/",
            "https://www.dingdiann.com/ddk_4/",
            "https://www.dingdiann.com/ddk_5/",
            "https://www.dingdiann.com/ddk_6/",
            "https://www.dingdiann.com/ddk_7/").toMutableList()

    }

    override fun parseRankUrls(document: Document): ArrayList<HashMap<String,String>> {
        val arrayList = ArrayList<HashMap<String,String>>()

        val mapOne = HashMap<String, String>()
        mapOne["name"] = "玄幻奇幻"
        val selectOne = document.select("#con_o1g_1 > ul > li")
        mapOne["size"] = selectOne.size.toString()
        for((index,value) in selectOne.withIndex()){
            val name = index.toString() + "name"
            val url = index.toString() + "url"
            mapOne[name] = value.select("a").text()
            mapOne[url] = value.select("a").attr("href")
        }
        arrayList.add(mapOne)



        val mapTwo = HashMap<String, String>()
        mapTwo["name"] = "武侠仙侠"
        val selectTwo = document.select("#con_o2g_1 > ul > li")
        mapTwo["size"] = selectTwo.size.toString()
        for((index,value) in selectTwo.withIndex()){
            val name = index.toString() + "name"
            val url = index.toString() + "url"
            mapTwo[name] = value.select("a").text()
            mapTwo[url] = value.select("a").attr("href")
        }
        arrayList.add(mapTwo)



        val mapThree = HashMap<String, String>()
        mapThree["name"] = "都市言情"
        val selectThree = document.select("#con_o3g_1 > ul > li")
        mapThree["size"] = selectThree.size.toString()
        for((index,value) in selectThree.withIndex()){
            val name = index.toString() + "name"
            val url = index.toString() + "url"
            mapThree[name] = value.select("a").text()
            mapThree[url] = value.select("a").attr("href")
        }
        arrayList.add(mapThree)



        val mapFour = HashMap<String, String>()
        mapFour["name"] = "历史军事"
        val selectFour = document.select("#con_o4g_1 > ul > li")
        mapFour["size"] = selectFour.size.toString()
        for((index,value) in selectFour.withIndex()){
            val name = index.toString() + "name"
            val url = index.toString() + "url"
            mapFour[name] = value.select("a").text()
            mapFour[url] = value.select("a").attr("href")
        }
        arrayList.add(mapFour)




        val mapFive = HashMap<String, String>()
        mapFive["name"] = "科幻灵异"
        val selectFive = document.select("#con_o5g_1 > ul > li")
        mapFive["size"] = selectFive.size.toString()
        for((index,value) in selectFive.withIndex()){
            val name = index.toString() + "name"
            val url = index.toString() + "url"
            mapFive[name] = value.select("a").text()
            mapFive[url] = value.select("a").attr("href")
        }
        arrayList.add(mapFive)



        val mapSix = HashMap<String, String>()
        mapSix["name"] = "网游竞技"
        val selectSix = document.select("#con_o6g_1 > ul > li")
        mapSix["size"] = selectSix.size.toString()
        for((index,value) in selectSix.withIndex()){
            val name = index.toString() + "name"
            val url = index.toString() + "url"
            mapSix[name] = value.select("a").text()
            mapSix[url] = value.select("a").attr("href")
        }
        arrayList.add(mapSix)



        val mapSeven = HashMap<String, String>()
        mapSeven["name"] = "女生频道"
        val selectSeven = document.select("#con_o7g_1 > ul > li")
        mapSeven["size"] = selectSeven.size.toString()
        for((index,value) in selectSeven.withIndex()){
            val name = index.toString() + "name"
            val url = index.toString() + "url"
            mapSeven[name] = value.select("a").text()
            mapSeven[url] = value.select("a").attr("href")
        }
        arrayList.add(mapSeven)



        val mapEight = HashMap<String, String>()
        mapEight["name"] = "排行总榜"
        val selectEight = document.select("#con_hng_1 > ul > li")
        mapEight["size"] = selectEight.size.toString()
        for((index,value) in selectEight.withIndex()){
            val name = index.toString() + "name"
            val url = index.toString() + "url"
            mapEight[name] = value.select("a").text()
            mapEight[url] = value.select("a").attr("href")
        }
        arrayList.add(mapEight)

        return arrayList
    }

    override fun parseTypeSeven(document: Document?): MutableList<BookDetail> {
        val select2 = document!!.select("#novelslist2 > div")
        val select1 = select2[2].select("ul > li")
        val bookDetailNovel = ArrayList<BookDetail>()
        for(value in select1) {
            val all = value.text()
            val bookName = value.select("a").text()
            val attr = value.select("a").attr("href")
            val authorName = all.replace(" ", "")
                .replace("/", "").replace(bookName, "")
            val bookDetail = BookDetail()
            bookDetail.bookName = bookName
            bookDetail.bookUrl = attr
            bookDetail.bookAuthor = authorName
            bookDetail.bookType = "网游竞技"
            bookDetail.baseUrl = "https://www.dingdiann.com/"
            bookDetailNovel.add(bookDetail)
        }
        return bookDetailNovel
    }
}