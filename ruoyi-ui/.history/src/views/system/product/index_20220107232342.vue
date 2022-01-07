<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="图号" prop="drawNumber">
        <el-input
          v-model="queryParams.drawNumber"
          placeholder="请输入图号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="规格" prop="specifications">
        <el-input
          v-model="queryParams.specifications"
          placeholder="请输入规格"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['system:product:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:product:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:product:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:product:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="productList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="图号" align="center" prop="drawNumber" />
      <el-table-column label="规格" align="center" prop="specifications" />
      <el-table-column label="单位" align="center" prop="unit" width="80" />
      <el-table-column label="备注 " align="center" prop="remark" />
      <el-table-column label="总数" align="center" prop="total"  width="80"/>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width" width="280">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleInput(scope.row)"
            v-hasPermi="['system:product:input']"
          >入库</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleOutput(scope.row)"
            v-hasPermi="['system:product:output']"
          >出库</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:product:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:product:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改商品对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="图号" prop="drawNumber">
          <el-input v-model="form.drawNumber" placeholder="请输入图号" />
        </el-form-item>
        <el-form-item label="规格" prop="specifications">
          <el-input v-model="form.specifications" placeholder="请输入规格" />
        </el-form-item>
        <el-form-item label="单位" prop="unit">
          <el-input v-model="form.unit" placeholder="请输入单位" />
        </el-form-item>
        <el-form-item label="备注 " prop="remark">
          <el-input v-model="form.remark" placeholder="请输入备注 " />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 商品入库对话框对话框 -->
    <el-dialog :title="title" :visible.sync="openInput" width="500px" append-to-body>
      <el-form ref="formInput" :model="formInput" :rules="rulesInput" label-width="80px">
        <el-form-item label="货架" prop="shelvesCellId">
          <el-cascader
            :props="shelvesTreeProps"
            v-model="formInput.shelvesCellId"
            separator="-"
            :emitPath="true"
            filterable
            clearable
            ></el-cascader>
        </el-form-item>
        <el-form-item label="数量" prop="count">
          <el-input v-model.number="formInput.count" placeholder="请输入数量" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitFormInput">入 库</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 商品出库对话框对话框 -->
    <el-dialog :title="title" :visible.sync="openOutput" width="500px" append-to-body>
      <el-form ref="formOutput" :model="formOutput" :rules="rulesOutput" label-width="80px">
        <el-table
          :data="productStroe"
          style="width: 100%;margin-bottom: 20px;"
          row-key="id"
          border
          default-expand-all="true"
          show-summary
          :tree-props="{children: 'children'}">
          <el-table-column
            prop="name"
            label="货柜/格子"
            width="180">
          </el-table-column>
          <el-table-column
            prop="count"
            label="库存数量"
            width="180">
          </el-table-column>
          <el-table-column
            prop="outputCount"
            label="出库数量">
            <template slot-scope="scope">
              <span v-if="scope.row.children && scope.row.children.length>0"></span>
              <el-input v-else v-model.number="scope.row.outputCount" 
                @change="handleOuptCountEdit(scope.$index, scope.row)"
                size="mini" ></el-input>
            </template>
          </el-table-column>
        </el-table>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitFormOutput">出 库</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listProduct, getProduct, delProduct, addProduct, updateProduct, getProductStore, output } from "@/api/system/product";
import { treeShelves,treeChildrenShelves } from "@/api/system/shelves";
import { addProductshelves } from "@/api/system/productshelves";
export default {
  name: "Product",
  data() {
    var validateOutputCount = (rule, value, callback) => {
        if (!Number.isInteger(value)) {
          callback(new Error('请输入数字值'));
        }
      };
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 商品表格数据
      productList: [],
      productStroe: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      openInput: false,
      openOutput: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        drawNumber: null,
        specifications: null,
      },
      // 表单参数
      form: {},
      formInput: {},
      formOutput: {},
      shelvesOptions: [],
      shelvesTreeProps: {
        lazy: true,
        lazyLoad (node, resolve) {
          const { level,root,data } = node;
          if(root){
            treeShelves().then((response) => {
              response.map(item=>{
                item.leaf=false;
              });
              resolve(response);
            });
          }else{
            treeChildrenShelves(data.value).then((response) => {
              response.map(item=>{
                item.leaf=true;
                item.disabled=(this.productIdByInput!=item.productId && item.productId);
                item.label=(this.productIdByInput==item.productId && item.productId?item.label+'(' +item.count+')':item.label)
              });
              resolve(response);
            });
          }
        }
      },
      // 表单校验
      rules: {
      },
      rulesInput:{
        shelvesCellId:[
        {
          required: true,
          message: '货架不能为空'
        },{
          type:'array',
          required: true,
          message: '货架不能为空',
          trigger:'change'
        }
        ],
        count:[{
          type: 'number',
          min: 0,
          message: '请填写正确数值',
          trigger: 'blur'
        },{
          required: true,
          message: '数量不能为空'
        }]
      },
      rulesOutput: {
        outputCount: [
          { validator: validateOutputCount, trigger: 'blur' }
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    handleOuptCountEdit() {
      if(!this.productStroe){
        return 0;
      }
      // TODO显示合计
    },
    /** 查询商品列表 */
    getList() {
      this.loading = true;
      listProduct(this.queryParams).then(response => {
        this.productList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.openInput = false;
      this.openOutput = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        drawNumber: null,
        specifications: null,
        unit: null,
        remark: null,
        createTime: null,
        updateTime: null,
        total: null
      };
      this.resetForm("form");
      this.formInput ={
        shelvesCellId: null,
        count: null,
        productId: null
      }
      this.resetForm("formInput");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加商品";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getProduct(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改商品";
      });
    },
    /** 入库按钮操作 */
    handleInput(row) {
      this.reset();
      const id = row.id || this.ids
      this.productIdByInput = id;
      this.formInput.productId=id;
      this.openInput = true;
      this.title = "入库商品";
    },

    /** 出库按钮操作 */
    handleOutput(row) {
      this.reset();
      
      const id = row.id || this.ids
      this.formOutput.productId=id;
      getProductStore(row).then((response) => {
        this.openOutput = true;
        this.title = "出库商品";
        this.productStroe=response;
        for(let i in this.productStroe){
          this.productStroe[i].id='p'+this.productStroe[i].id;
        }
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateProduct(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addProduct(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    submitFormInput() {
      this.$refs["formInput"].validate(valid => {
        if (valid) {
          if(this.formInput.shelvesCellId){
            this.formInput.shelvesCellId=parseInt(this.formInput.shelvesCellId[this.formInput.shelvesCellId.length-1]);
          }
          
          addProductshelves(this.formInput).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.openInput = false;
              this.getList();
            });
        }
      });
    },
    submitFormOutput() {
      let request = this.getOutputObject(this.productStroe);
      if(request.length>0){
        output(request).then(response => {
          this.$modal.msgSuccess("出库成功");
          this.openOutput = false;
          this.getList();
        });
      }
    },
    getOutputObject(outputs){
      let arr = [];
      for(let j=0;j<outputs.length; j++){
        let parent = outputs[j];
        if(parent.children && parent.children.length>0){
          arr = arr.concat(this.getOutputObject(parent.children));
        } else if(parent.outputCount && parent.outputCount>0){
          arr.push(parent);
          console.log('==',parent)
        }
      }
      return arr;
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除商品编号为"' + ids + '"的数据项？').then(function() {
        return delProduct(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/product/export', {
        ...this.queryParams
      }, `product_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
