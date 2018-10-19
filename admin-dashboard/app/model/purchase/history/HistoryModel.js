Ext.define('Admin.model.purchase.history.HistoryModel', {
    extend: 'Admin.model.Base',
    requires: [
        'Ext.data.proxy.Rest',
    ],
    fields: [
        { type: 'int', name: 'id' },
        { type: 'string', name: 'purchaseNum' },
        { type: 'float', name: 'statement' },
        { type: 'float', name: 'payment' },
        { type: 'string', name: 'method' },
        { type: 'date', name: 'purchaseTime', dateFormat: 'Y/m/d H:i:s' },
        { type: 'string', name: 'remark' },
        { type: 'int', name: 'workNum', mapping: 'operator.workNum' },
        { type: 'string', name: 'userRealName', mapping: 'operator.userRealName' },
        { type: 'string', name: 'supplierName', mapping: 'supplier.supplierName' },
        { type: 'string', name: 'repoName', mapping: 'repo.repoName' }
    ],

    proxy: {
        type: 'rest',
        url: '/purchasehistory',
    }
});
