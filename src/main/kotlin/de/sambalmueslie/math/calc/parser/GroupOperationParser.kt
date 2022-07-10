package de.sambalmueslie.math.calc.parser


import org.slf4j.Logger
import org.slf4j.LoggerFactory

class GroupOperationParser(private val context: ParserContext) : Parser, ParserChangeListener {

    companion object {
        val logger: Logger = LoggerFactory.getLogger(GroupOperationParser::class.java)
        private const val START_OF_GROUP = '('
        private const val END_OF_GROUP = ')'
    }

    init {
        context.listener.add(this)
    }

    override fun suitable(c: Char): Boolean {
        return c == START_OF_GROUP || context.openGroups.isNotEmpty()
    }

    private var ignoreNext: Boolean = false

    override fun process(index: Int, c: Char) {
        when (c) {
            START_OF_GROUP -> handleStartOfGroup(index)
            END_OF_GROUP -> handleEndOfGroup(index)
        }
    }


    private fun handleStartOfGroup(index: Int) {
        context.openGroups.add(OperationGroup())
    }

    private fun handleEndOfGroup(index: Int) {
        if (ignoreNext) {
            ignoreNext = false
            return
        }
        logger.info("Found group at $index ")
        val group = context.openGroups.removeLastOrNull() ?: throw IllegalStateException("Cannot parse equation invalid symbol at $index")
        group.finishProcessing()
        context.root.add(group.getOperation())
    }

    override fun parserFinished(index: Int, c: Char) {
        if (c == END_OF_GROUP) {
            context.openGroups.removeLast()
            ignoreNext = true
        }
    }
}
