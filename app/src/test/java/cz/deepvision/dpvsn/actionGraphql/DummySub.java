package cz.deepvision.dpvsn.actionGraphql;

import com.apollographql.apollo.api.InputFieldMarshaller;
import com.apollographql.apollo.api.Operation;
import com.apollographql.apollo.api.OperationName;
import com.apollographql.apollo.api.ResponseField;
import com.apollographql.apollo.api.ResponseFieldMapper;
import com.apollographql.apollo.api.ResponseFieldMarshaller;
import com.apollographql.apollo.api.ResponseReader;
import com.apollographql.apollo.api.ResponseWriter;
import com.apollographql.apollo.api.Subscription;
import com.apollographql.apollo.api.internal.UnmodifiableMapBuilder;
import com.apollographql.apollo.api.internal.Utils;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class DummySub implements Subscription<DummySub.Data, DummySub.Data, DummySub.Variables> {
    public static final String OPERATION_ID = "831fd8f7220a807b03d3417be8518d73d09feda693df766eb26e56510aaff5c2";

    public static final String QUERY_DOCUMENT = "subscription UpdatedOrderCountCategory($branches: [ID!]!, $categories: [OrderStateCategoryEnum!]!) {\n"
            + "  updatedOrderCategoryCount(companyBranches: $branches, orderStateCategories: $categories) {\n"
            + "    __typename\n"
            + "    newOrderStateCategory\n"
            + "    order {\n"
            + "      __typename\n"
            + "      id\n"
            + "      orderStates {\n"
            + "        __typename\n"
            + "        enum\n"
            + "      }\n"
            + "    }\n"
            + "  }\n"
            + "}";

    public static final OperationName OPERATION_NAME = new OperationName() {
        @Override
        public String name() {
            return "UpdatedOrderCountCategory";
        }
    };

    private final DummySub.Variables variables;

    public DummySub(List<String> branches,
                    List<String> categories) {
        Utils.checkNotNull(branches, "branches == null");
        Utils.checkNotNull(categories, "categories == null");
        variables = new DummySub.Variables(branches, categories);
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public String operationId() {
        return OPERATION_ID;
    }

    @Override
    public String queryDocument() {
        return QUERY_DOCUMENT;
    }

    @Override
    public DummySub.Data wrapData(DummySub.Data data) {
        return data;
    }

    @Override
    public DummySub.Variables variables() {
        return variables;
    }

    @Override
    public ResponseFieldMapper<DummySub.Data> responseFieldMapper() {
        return new Data.Mapper();
    }

    @Override
    public OperationName name() {
        return OPERATION_NAME;
    }

    public static final class Builder {
        private List<String> branches;

        private List<String> categories;

        Builder() {
        }

        public Builder branches(List<String> branches) {
            this.branches = branches;
            return this;
        }

        public Builder categories(List<String> categories) {
            this.categories = categories;
            return this;
        }

        public DummySub build() {
            Utils.checkNotNull(branches, "branches == null");
            Utils.checkNotNull(categories, "categories == null");
            return new DummySub(branches, categories);
        }
    }

    public static final class Variables extends Operation.Variables {
        private final List<String> branches;

        private final List<String> categories;

        private final transient Map<String, Object> valueMap = new LinkedHashMap<>();

        Variables(List<String> branches, List<String> categories) {
            this.branches = branches;
            this.categories = categories;
            this.valueMap.put("branches", branches);
            this.valueMap.put("categories", categories);
        }

        public List<String> branches() {
            return branches;
        }

        public List<String> categories() {
            return categories;
        }

        @Override
        public Map<String, Object> valueMap() {
            return Collections.unmodifiableMap(valueMap);
        }

        @Override
        public InputFieldMarshaller marshaller() {
            return null;
        }

    }

    public static class Data implements Operation.Data {
        static final ResponseField[] $responseFields = {
                ResponseField.forObject("updatedOrderCategoryCount", "updatedOrderCategoryCount", new UnmodifiableMapBuilder<String, Object>(2)
                        .put("companyBranches", new UnmodifiableMapBuilder<String, Object>(2)
                                .put("kind", "Variable")
                                .put("variableName", "branches")
                                .build())
                        .put("orderStateCategories", new UnmodifiableMapBuilder<String, Object>(2)
                                .put("kind", "Variable")
                                .put("variableName", "categories")
                                .build())
                        .build(), false, Collections.<ResponseField.Condition>emptyList())
        };

        final UpdatedOrderCategoryCount updatedOrderCategoryCount;

        private transient volatile String $toString;

        private transient volatile int $hashCode;

        private transient volatile boolean $hashCodeMemoized;

        public Data(UpdatedOrderCategoryCount updatedOrderCategoryCount) {
            this.updatedOrderCategoryCount = Utils.checkNotNull(updatedOrderCategoryCount, "updatedOrderCategoryCount == null");
        }

        public UpdatedOrderCategoryCount updatedOrderCategoryCount() {
            return this.updatedOrderCategoryCount;
        }

        public ResponseFieldMarshaller marshaller() {
            return new ResponseFieldMarshaller() {
                @Override
                public void marshal(ResponseWriter writer) {
                    writer.writeObject($responseFields[0], updatedOrderCategoryCount.marshaller());
                }
            };
        }

        @Override
        public String toString() {
            if ($toString == null) {
                $toString = "Data{"
                        + "updatedOrderCategoryCount=" + updatedOrderCategoryCount
                        + "}";
            }
            return $toString;
        }

        @Override
        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (o instanceof Data) {
                Data that = (Data) o;
                return this.updatedOrderCategoryCount.equals(that.updatedOrderCategoryCount);
            }
            return false;
        }

        @Override
        public int hashCode() {
            if (!$hashCodeMemoized) {
                int h = 1;
                h *= 1000003;
                h ^= updatedOrderCategoryCount.hashCode();
                $hashCode = h;
                $hashCodeMemoized = true;
            }
            return $hashCode;
        }

        public static final class Mapper implements ResponseFieldMapper<Data> {
            final UpdatedOrderCategoryCount.Mapper updatedOrderCategoryCountFieldMapper = new UpdatedOrderCategoryCount.Mapper();

            @Override
            public Data map(ResponseReader reader) {
                final UpdatedOrderCategoryCount updatedOrderCategoryCount = reader.readObject($responseFields[0], new ResponseReader.ObjectReader<UpdatedOrderCategoryCount>() {
                    @Override
                    public UpdatedOrderCategoryCount read(ResponseReader reader) {
                        return updatedOrderCategoryCountFieldMapper.map(reader);
                    }
                });
                return new Data(updatedOrderCategoryCount);
            }
        }
    }

    public static class UpdatedOrderCategoryCount {
        static final ResponseField[] $responseFields = {
                ResponseField.forString("__typename", "__typename", null, false, Collections.<ResponseField.Condition>emptyList()),
                ResponseField.forString("newOrderStateCategory", "newOrderStateCategory", null, false, Collections.<ResponseField.Condition>emptyList()),
                ResponseField.forObject("order", "order", null, false, Collections.<ResponseField.Condition>emptyList())
        };

        final String __typename;

        final String newOrderStateCategory;

        final Order order;

        private transient volatile String $toString;

        private transient volatile int $hashCode;

        private transient volatile boolean $hashCodeMemoized;

        public UpdatedOrderCategoryCount(String __typename,
                                         String newOrderStateCategory, Order order) {
            this.__typename = Utils.checkNotNull(__typename, "__typename == null");
            this.newOrderStateCategory = Utils.checkNotNull(newOrderStateCategory, "newOrderStateCategory == null");
            this.order = Utils.checkNotNull(order, "order == null");
        }

        public String __typename() {
            return this.__typename;
        }

        public String newOrderStateCategory() {
            return this.newOrderStateCategory;
        }

        public Order order() {
            return this.order;
        }

        public ResponseFieldMarshaller marshaller() {
            return new ResponseFieldMarshaller() {
                @Override
                public void marshal(ResponseWriter writer) {
                    writer.writeString($responseFields[0], __typename);
                    writer.writeString($responseFields[1], newOrderStateCategory);
                    writer.writeObject($responseFields[2], order.marshaller());
                }
            };
        }

        @Override
        public String toString() {
            if ($toString == null) {
                $toString = "UpdatedOrderCategoryCount{"
                        + "__typename=" + __typename + ", "
                        + "newOrderStateCategory=" + newOrderStateCategory + ", "
                        + "order=" + order
                        + "}";
            }
            return $toString;
        }

        @Override
        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (o instanceof UpdatedOrderCategoryCount) {
                UpdatedOrderCategoryCount that = (UpdatedOrderCategoryCount) o;
                return this.__typename.equals(that.__typename)
                        && this.newOrderStateCategory.equals(that.newOrderStateCategory)
                        && this.order.equals(that.order);
            }
            return false;
        }

        @Override
        public int hashCode() {
            if (!$hashCodeMemoized) {
                int h = 1;
                h *= 1000003;
                h ^= __typename.hashCode();
                h *= 1000003;
                h ^= newOrderStateCategory.hashCode();
                h *= 1000003;
                h ^= order.hashCode();
                $hashCode = h;
                $hashCodeMemoized = true;
            }
            return $hashCode;
        }

        public static final class Mapper implements ResponseFieldMapper<UpdatedOrderCategoryCount> {
            final Order.Mapper orderFieldMapper = new Order.Mapper();

            @Override
            public UpdatedOrderCategoryCount map(ResponseReader reader) {
                final String __typename = reader.readString($responseFields[0]);
                final String newOrderStateCategoryStr = reader.readString($responseFields[1]);
                final String newOrderStateCategory;
                if (newOrderStateCategoryStr != null) {
                    newOrderStateCategory = newOrderStateCategoryStr;
                } else {
                    newOrderStateCategory = null;
                }
                final Order order = reader.readObject($responseFields[2], new ResponseReader.ObjectReader<Order>() {
                    @Override
                    public Order read(ResponseReader reader) {
                        return orderFieldMapper.map(reader);
                    }
                });
                return new UpdatedOrderCategoryCount(__typename, newOrderStateCategory, order);
            }
        }
    }

    public static class Order {
        static final ResponseField[] $responseFields = {
                ResponseField.forString("__typename", "__typename", null, false, Collections.<ResponseField.Condition>emptyList()),
                ResponseField.forCustomType("id", "id", null, false, null, Collections.<ResponseField.Condition>emptyList()),
                ResponseField.forList("orderStates", "orderStates", null, false, Collections.<ResponseField.Condition>emptyList())
        };

        final String __typename;

        final String id;

        final List<OrderState> orderStates;

        private transient volatile String $toString;

        private transient volatile int $hashCode;

        private transient volatile boolean $hashCodeMemoized;

        public Order(String __typename, String id,
                     List<OrderState> orderStates) {
            this.__typename = Utils.checkNotNull(__typename, "__typename == null");
            this.id = Utils.checkNotNull(id, "id == null");
            this.orderStates = Utils.checkNotNull(orderStates, "orderStates == null");
        }

        public String __typename() {
            return this.__typename;
        }

        public String id() {
            return this.id;
        }

        public List<OrderState> orderStates() {
            return this.orderStates;
        }

        public ResponseFieldMarshaller marshaller() {
            return new ResponseFieldMarshaller() {
                @Override
                public void marshal(ResponseWriter writer) {
                    writer.writeString($responseFields[0], __typename);
                    writer.writeCustom((ResponseField.CustomTypeField) $responseFields[1], id);
                    writer.writeList($responseFields[2], orderStates, new ResponseWriter.ListWriter() {
                        @Override
                        public void write(List items, ResponseWriter.ListItemWriter listItemWriter) {
                            for (Object item : items) {
                                listItemWriter.writeObject(((OrderState) item).marshaller());
                            }
                        }
                    });
                }
            };
        }

        @Override
        public String toString() {
            if ($toString == null) {
                $toString = "Order{"
                        + "__typename=" + __typename + ", "
                        + "id=" + id + ", "
                        + "orderStates=" + orderStates
                        + "}";
            }
            return $toString;
        }

        @Override
        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (o instanceof Order) {
                Order that = (Order) o;
                return this.__typename.equals(that.__typename)
                        && this.id.equals(that.id)
                        && this.orderStates.equals(that.orderStates);
            }
            return false;
        }

        @Override
        public int hashCode() {
            if (!$hashCodeMemoized) {
                int h = 1;
                h *= 1000003;
                h ^= __typename.hashCode();
                h *= 1000003;
                h ^= id.hashCode();
                h *= 1000003;
                h ^= orderStates.hashCode();
                $hashCode = h;
                $hashCodeMemoized = true;
            }
            return $hashCode;
        }

        public static final class Mapper implements ResponseFieldMapper<Order> {
            final OrderState.Mapper orderStateFieldMapper = new OrderState.Mapper();

            @Override
            public Order map(ResponseReader reader) {
                final String __typename = reader.readString($responseFields[0]);
                final String id = reader.readCustomType((ResponseField.CustomTypeField) $responseFields[1]);
                final List<OrderState> orderStates = reader.readList($responseFields[2], new ResponseReader.ListReader<OrderState>() {
                    @Override
                    public OrderState read(ResponseReader.ListItemReader listItemReader) {
                        return listItemReader.readObject(new ResponseReader.ObjectReader<OrderState>() {
                            @Override
                            public OrderState read(ResponseReader reader) {
                                return orderStateFieldMapper.map(reader);
                            }
                        });
                    }
                });
                return new Order(__typename, id, orderStates);
            }
        }
    }

    public static class OrderState {
        static final ResponseField[] $responseFields = {
                ResponseField.forString("__typename", "__typename", null, false, Collections.<ResponseField.Condition>emptyList()),
                ResponseField.forString("enum", "enum", null, false, Collections.<ResponseField.Condition>emptyList())
        };

        final String __typename;

        final String enum_;

        private transient volatile String $toString;

        private transient volatile int $hashCode;

        private transient volatile boolean $hashCodeMemoized;

        public OrderState(String __typename, String enum_) {
            this.__typename = Utils.checkNotNull(__typename, "__typename == null");
            this.enum_ = Utils.checkNotNull(enum_, "enum_ == null");
        }

        public String __typename() {
            return this.__typename;
        }

        /**
         * enum string used to identify object
         */
        public String enum_() {
            return this.enum_;
        }

        public ResponseFieldMarshaller marshaller() {
            return new ResponseFieldMarshaller() {
                @Override
                public void marshal(ResponseWriter writer) {
                    writer.writeString($responseFields[0], __typename);
                    writer.writeString($responseFields[1], enum_);
                }
            };
        }

        @Override
        public String toString() {
            if ($toString == null) {
                $toString = "OrderState{"
                        + "__typename=" + __typename + ", "
                        + "enum_=" + enum_
                        + "}";
            }
            return $toString;
        }

        @Override
        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (o instanceof OrderState) {
                OrderState that = (OrderState) o;
                return this.__typename.equals(that.__typename)
                        && this.enum_.equals(that.enum_);
            }
            return false;
        }

        @Override
        public int hashCode() {
            if (!$hashCodeMemoized) {
                int h = 1;
                h *= 1000003;
                h ^= __typename.hashCode();
                h *= 1000003;
                h ^= enum_.hashCode();
                $hashCode = h;
                $hashCodeMemoized = true;
            }
            return $hashCode;
        }

        public static final class Mapper implements ResponseFieldMapper<OrderState> {
            @Override
            public OrderState map(ResponseReader reader) {
                final String __typename = reader.readString($responseFields[0]);
                final String enum_Str = reader.readString($responseFields[1]);
                final String enum_;
                if (enum_Str != null) {
                    enum_ = enum_Str;
                } else {
                    enum_ = null;
                }
                return new OrderState(__typename, enum_);
            }
        }
    }
}
