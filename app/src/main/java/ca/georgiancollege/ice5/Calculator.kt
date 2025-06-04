package ca.georgiancollege.ice5

import android.widget.Button
import ca.georgiancollege.ice5.databinding.ActivityMainBinding

class Calculator(private var binding: ActivityMainBinding) {

    private lateinit var numberButtons: List<Button>
    private lateinit var operatorButtons: List<Button>
    private lateinit var modifierButtons: List<Button>

    private var currentOperand: String? = null
    private var currentOperator: String? = null
    private var operationInProgress = false

    init {
        initializeButtonLists(binding)
        configureNumberInput()
        configureOperatorButtons()
        configureModifierButtons()

    }

    private fun initializeButtonLists(binding: ActivityMainBinding) {
        numberButtons = listOf(
            binding.zeroButton, binding.oneButton, binding.twoButton,
            binding.threeButton, binding.fourButton, binding.fiveButton,
            binding.sixButton, binding.sevenButton, binding.eightButton,
            binding.nineButton, binding.decimalButton
        )

        operatorButtons = listOf(
            binding.plusButton, binding.minusButton,
            binding.multiplyButton, binding.divideButton, binding.equalsButton
        )

        modifierButtons = listOf(
            binding.plusMinusButton,
            binding.clearButton, binding.deleteButton
        )
    }

    private fun configureNumberInput() {
        numberButtons.forEach { button ->
            button.setOnClickListener {
                val input = button.text.toString()
                val currentResultText = binding.resultEditText.text.toString()

                if (operationInProgress && currentOperator != null) {
                    binding.resultEditText.setText("0")
                    operationInProgress = false
                }

                if (input == "." && currentResultText.contains(".")) {
                    return@setOnClickListener
                }

                if (currentResultText == "0" && input != ".") {
                    binding.resultEditText.setText(input)
                } else {
                    binding.resultEditText.append(input)
                }
            }
        }
    }

    private fun configureOperatorButtons() {
        operatorButtons.forEach { button ->
            button.setOnClickListener {
                val inputValue = binding.resultEditText.text.toString()

                when (button) {
                    binding.plusButton, binding.minusButton, binding.multiplyButton, binding.divideButton -> {
                        // Only allow operator if not already chosen
                        if (currentOperator == null) {
                            currentOperand = inputValue
                            currentOperator = when (button) {
                                binding.plusButton -> "add"
                                binding.minusButton -> "subtract"
                                binding.multiplyButton -> "multiply"
                                binding.divideButton -> "divide"
                                else -> null
                            }
                            binding.resultEditText.setText("0")
                        }
                    }
                }
            }
        }
    }


}

