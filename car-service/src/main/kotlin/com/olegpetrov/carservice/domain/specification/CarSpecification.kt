package com.olegpetrov.carservice.domain.specification

import com.olegpetrov.carservice.domain.Car
import com.olegpetrov.carservice.domain.Make
import com.olegpetrov.carservice.domain.Manager
import com.olegpetrov.carservice.domain.Model
import com.olegpetrov.carservice.domain.enums.Transmission
import com.olegpetrov.carservice.domain.options.CarIncludeOptions
import com.olegpetrov.carservice.dto.SearchCarDto
import jakarta.persistence.criteria.Join
import jakarta.persistence.criteria.JoinType
import jakarta.persistence.criteria.Predicate
import org.springframework.data.jpa.domain.Specification

class CarSpecification {
    companion object {
        fun createSpecification(
            searchParams: SearchCarDto,
            includeOptions: CarIncludeOptions
        ): Specification<Car> {
            return Specification { root, _, cb ->
                val predicates = mutableListOf<Predicate>()

                // todo fix n + 1
                val modelFetch = root.join<Car, Model>("model") as Join<Car, Model>
                val makeFetch = modelFetch.join<Model, Make>("make") as Join<Model, Make>

                if (includeOptions.includeManager) {
                    root.join<Car, Manager>("manager", JoinType.LEFT)
                }

                if (includeOptions.includePhotos) {
                    root.join<Car, List<String>>("photos", JoinType.LEFT)
                }

                searchParams.id?.let { predicates.add(cb.equal(root.get<Long>("id"), it)) }

                searchParams.ids?.let {
                    predicates.add(root.get<Long>("id").`in`(it))
                }

                searchParams.make?.let {
                    predicates.add(
                        cb.equal(
                            makeFetch.get<String>("name"), it
                        )
                    )
                }

                searchParams.model?.let {
                    predicates.add(
                        cb.equal(
                            modelFetch.get<String>("name"), it
                        )
                    )
                }

                searchParams.minPrice?.let { predicates.add(cb.greaterThanOrEqualTo(root.get("price"), it)) }
                searchParams.maxPrice?.let { predicates.add(cb.lessThanOrEqualTo(root.get("price"), it)) }
                searchParams.minYear?.let { predicates.add(cb.greaterThanOrEqualTo(root.get("year"), it)) }
                searchParams.maxYear?.let { predicates.add(cb.lessThanOrEqualTo(root.get("year"), it)) }
                searchParams.minMileage?.let { predicates.add(cb.greaterThanOrEqualTo(root.get("mileage"), it)) }
                searchParams.maxMileage?.let { predicates.add(cb.lessThanOrEqualTo(root.get("mileage"), it)) }

                searchParams.transmission?.let { predicates.add(cb.equal(root.get<Transmission>("transmission"), it)) }

                cb.and(*predicates.toTypedArray())
            }
        }

        fun createSpecification(
            searchParams: SearchCarDto,
        ): Specification<Car> {
            return createSpecification(searchParams, CarIncludeOptions())
        }
    }
}
