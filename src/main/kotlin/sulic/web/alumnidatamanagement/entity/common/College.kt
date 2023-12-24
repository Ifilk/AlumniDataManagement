package sulic.web.alumnidatamanagement.entity.common

enum class College(open val chineseName: String, open val englishName: String) {
    ENERGY_AND_ENVIRONMENTAL_SCIENCES("能源与环境学院", "College of Energy and Environmental Sciences"),
    ENVIRONMENTAL_AND_CHEMICAL_ENGINEERING("环境与化学工程学院", "College of Environmental and Chemical Engineering"),
    ELECTRICAL_ENGINEERING("电气工程学院", "College of Electrical Engineering"),
    AUTOMATION_ENGINEERING("自动化工程学院", "College of Automation Engineering"),
    COMPUTER_SCIENCE_AND_TECHNOLOGY("计算机科学与技术", "College of Computer Science and Technology"),
    ELECTRONIC_INFORMATION_ENGINEERING("电子信息工程学院", "College of Electronic Information Engineering"),
    ECONOMICS_AND_MANAGEMENT("经济与管理学院", "College of Economics and Management"),
    MATHEMATICS_AND_PHYSICS("数理学院", "College of Mathematics and Physics"),
    FOREIGN_LANGUAGES("外国语学院", "College of Foreign Languages"),
    SPORTS("体育学院", "College of Sports"),
    MARXISM("马克思主义学院", "College of Marxism"),
    HUMANITIES_AND_ARTS("人文艺术学院", "College of Humanities and Arts");

    companion object{
        fun findCollegeByName(name: String): College? {
            return entries.find { it.chineseName == name || it.englishName == name }
        }
    }
}
