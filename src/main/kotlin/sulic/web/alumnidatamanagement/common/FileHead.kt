package sulic.web.alumnidatamanagement.common

object FileHead {
    val FILE_TYPE_MAP = mutableMapOf<String, String>()
    init {
        FILE_TYPE_MAP["jpg"] = "FFD8FF" //JPEG
        FILE_TYPE_MAP["png"] = "89504E47" //PNG
        FILE_TYPE_MAP["gif"] = "47494638" //GIF
        FILE_TYPE_MAP["tif"] = "49492A00" //TIFF
        FILE_TYPE_MAP["bmp"] = "424D" //Windows Bitmap
        FILE_TYPE_MAP["dwg"] = "41433130" //CAD
        FILE_TYPE_MAP["html"] = "68746D6C3E" //HTML
        FILE_TYPE_MAP["rtf"] = "7B5C727466" //Rich Text Format
        FILE_TYPE_MAP["xml"] = "3C3F786D6C"
        FILE_TYPE_MAP["zip"] = "504B0304"
        FILE_TYPE_MAP["rar"] = "52617221"
        FILE_TYPE_MAP["psd"] = "38425053" //PhotoShop
        FILE_TYPE_MAP["eml"] = "44656C69766572792D646174653A" //Email [thorough only]
        FILE_TYPE_MAP["dbx"] = "CFAD12FEC5FD746F" //Outlook Express
        FILE_TYPE_MAP["pst"] = "2142444E" //Outlook
        FILE_TYPE_MAP["office"] = "D0CF11E0" //office类型，包括doc、xls和ppt
        FILE_TYPE_MAP["mdb"] = "000100005374616E64617264204A" //MS Access
        FILE_TYPE_MAP["wpd"] = "FF575043" //WordPerfect
        FILE_TYPE_MAP["eps"] = "252150532D41646F6265"
        FILE_TYPE_MAP["ps"] = "252150532D41646F6265"
        FILE_TYPE_MAP["pdf"] = "255044462D312E" //Adobe Acrobat
        FILE_TYPE_MAP["qdf"] = "AC9EBD8F" //Quicken
        FILE_TYPE_MAP["pwl"] = "E3828596" //Windows Password
        FILE_TYPE_MAP["wav"] = "57415645" //Wave
        FILE_TYPE_MAP["avi"] = "41564920"
        FILE_TYPE_MAP["ram"] = "2E7261FD" //Real Audio
        FILE_TYPE_MAP["rm"] = "2E524D46" //Real Media
        FILE_TYPE_MAP["mpg"] = "000001BA" //
        FILE_TYPE_MAP["mov"] = "6D6F6F76" //Quicktime
        FILE_TYPE_MAP["asf"] = "3026B2758E66CF11" //Windows Media
        FILE_TYPE_MAP["mid"] = "4D546864" //MIDI (mid)
    }

    // 获得文件头部字符串
    fun bytesToHexString(src: ByteArray?): String? {
        val stringBuilder = StringBuilder()
        if (src == null || src.isEmpty()) {
            return null
        }
        for (i in src.indices) {
            val v = src[i].toInt() and 0xFF
            val hv = Integer.toHexString(v)
            if (hv.length < 2) {
                stringBuilder.append(0)
            }
            stringBuilder.append(hv)
        }
        return stringBuilder.toString()
    }

}