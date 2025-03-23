package com.olegpetrov.carservice.domain.specification

import org.springframework.data.domain.Sort

class SortingUtils {
    companion object {

        fun getSort(sortStrings: List<String>): Sort {
            val orders = sortStrings.map { sortString ->
                val direction = getDirection(sortString)
                val property = getProperty(sortString)
                Sort.Order(direction, property)
            }
            return Sort.by(orders)
        }

        private fun getDirection(direction: String): Sort.Direction {
            return when {
                direction.startsWith("+") -> Sort.Direction.ASC
                direction.startsWith("-") -> Sort.Direction.DESC
                else -> throw IllegalArgumentException("Invalid sorting direction. Use '+' for ascending or '-' for descending.")
            }
        }

        private fun getProperty(direction: String): String {
            return direction.substring(1)
        }
    }
}
